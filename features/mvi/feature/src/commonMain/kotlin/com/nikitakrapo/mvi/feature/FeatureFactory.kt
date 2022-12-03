package com.nikitakrapo.mvi.feature

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

interface FeatureFactory {
    fun <Intent : Any, Action : Any, Effect : Any, State : Any, Event : Any> create(
        name: String? = null,
        initialState: State,
        intentToAction: IntentToAction<Intent, Action>,
        reducer: Reducer<State, Effect>,
        actor: Actor<Action, State, Effect>,
        bootstrapper: Bootstrapper<Action>? = null,
        eventsPublisher: EventsPublisher<Action, Effect, State, Event>? = null,
        coroutineContext: CoroutineContext = Dispatchers.Main.immediate,
        threadVerifier: ThreadVerifier = ThreadVerifier(),
    ): Feature<Intent, State, Event>
}

fun FeatureFactory() = object : FeatureFactory {
    override fun <Intent : Any, Action : Any, Effect : Any, State : Any, Event : Any> create(
        name: String?,
        initialState: State,
        intentToAction: IntentToAction<Intent, Action>,
        reducer: Reducer<State, Effect>,
        actor: Actor<Action, State, Effect>,
        bootstrapper: Bootstrapper<Action>?,
        eventsPublisher: EventsPublisher<Action, Effect, State, Event>?,
        coroutineContext: CoroutineContext,
        threadVerifier: ThreadVerifier
    ): Feature<Intent, State, Event> = object : Feature<Intent, State, Event> {
        private val stateFlow = MutableStateFlow(initialState)
        private val actionFlow = MutableSharedFlow<Action>()
        private val eventsFlow = MutableSharedFlow<Event>()
        private val scope = CoroutineScope(coroutineContext)

        override val state: StateFlow<State> get() = stateFlow.asStateFlow()
        override val events: SharedFlow<Event> get() = eventsFlow.asSharedFlow()
        override val isDisposed: Boolean get() = scope.isActive.not()

        init {
            scope.launch {
                actionFlow.collect { action ->
                    launch(start = CoroutineStart.UNDISPATCHED) {
                        invokeActor(action)
                    }
                }
            }
            bootstrapper?.let { bootstrapper ->
                scope.launch {
                    actionFlow.emitAll(bootstrapper())
                }
            }
        }

        private suspend fun invokeActor(action: Action) {
            actor(action, stateFlow.value)
                .collect { effect -> invokeReducer(action, effect) }
        }

        private suspend fun invokeReducer(action: Action, effect: Effect) {
            threadVerifier.verify("reducer")
            val state = stateFlow.value
            val newState = reducer.run { state.invoke(effect) }
            stateFlow.emit(newState)
            invokeEventsPublisher(action, effect, state)
        }

        private suspend fun invokeEventsPublisher(action: Action, effect: Effect, state: State) {
            eventsPublisher?.invoke(action, effect, state)?.let { event ->
                eventsFlow.emit(event)
            }
        }

        override fun accept(intent: Intent) {
            threadVerifier.verify("accept")
            scope.launch {
                ensureActive()
                val action = intentToAction(intent)
                actionFlow.emit(action)
            }
        }

        override fun dispose() {
            threadVerifier.verify("dispose")
            scope.cancel()
        }
    }
}

fun <Intent : Any, State : Any> FeatureFactory.createReducerFeature(
    name: String? = null,
    initialState: State,
    reducer: Reducer<State, Intent>,
    coroutineContext: CoroutineContext = Dispatchers.Main.immediate,
): Feature<Intent, State, Nothing> = create(
    name = name,
    initialState = initialState,
    intentToAction = { it },
    reducer = reducer,
    actor = { intent, _ -> flowOf(intent) },
    coroutineContext = coroutineContext,
)