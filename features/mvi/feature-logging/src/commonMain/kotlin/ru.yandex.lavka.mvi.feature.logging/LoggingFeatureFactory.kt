package ru.yandex.lavka.mvi.feature.logging

import com.nikitakrapo.mvi.feature.Actor
import com.nikitakrapo.mvi.feature.Bootstrapper
import com.nikitakrapo.mvi.feature.EventsPublisher
import com.nikitakrapo.mvi.feature.Feature
import com.nikitakrapo.mvi.feature.FeatureFactory
import com.nikitakrapo.mvi.feature.IntentToAction
import com.nikitakrapo.mvi.feature.Reducer
import com.nikitakrapo.mvi.feature.ThreadVerifier
import kotlinx.coroutines.flow.onEach
import kotlin.coroutines.CoroutineContext

fun LoggingFeatureFactory(
    delegate: FeatureFactory,
    logger: Logger
): FeatureFactory = object : FeatureFactory {

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
    ): Feature<Intent, State, Event> {
        if (name == null) {
            return delegate.create(
                name = name,
                initialState = initialState,
                intentToAction = intentToAction,
                reducer = reducer,
                actor = actor,
                bootstrapper = bootstrapper,
                eventsPublisher = eventsPublisher,
                coroutineContext = coroutineContext,
                threadVerifier = threadVerifier
            )
        }

        logger.log("$name: create()")

        val origin = delegate.create<Intent, Action, Effect, State, Event>(
            name = name,
            initialState = initialState,
            reducer = { effect ->
                reducer.run { invoke(effect) }.also { newState ->
                    logger.log("$name: reducer($effect) -> $newState")
                }
            },
            intentToAction = { intent ->
                intentToAction.invoke(intent).also { action ->
                    logger.log("$name: intentToAction($intent) -> $action")
                }
            },
            bootstrapper = bootstrapper?.let {
                Bootstrapper {
                    bootstrapper()
                        .onEach { action ->
                            logger.log("$name: bootstrapper() -> $action")
                        }
                }
            },
            actor = { action, state ->
                actor(action, state)
                    .onEach { effect ->
                        logger.log("$name: actor($action, $state) -> $effect")
                    }
            },
            eventsPublisher = eventsPublisher?.let {
                EventsPublisher { action, effect, state ->
                    eventsPublisher(action, effect, state).also { event ->
                        event?.let { logger.log("$name: eventsPublisher($action, $effect, $state) -> $event") }
                    }
                }
            },
            coroutineContext = coroutineContext,
            threadVerifier = threadVerifier
        )

        return object : Feature<Intent, State, Event> by origin {

            override fun accept(intent: Intent) {
                logger.log("$name: accept($intent)")
                origin.accept(intent)
            }

            override fun dispose() {
                logger.log("$name: dispose()")
                origin.dispose()
            }
        }
    }
}
