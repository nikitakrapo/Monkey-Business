package com.nikitakrapo.monkeybusiness.home

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import kotlin.test.BeforeTest

@OptIn(ExperimentalCoroutinesApi::class)
class HomeComponentTests {

    @BeforeTest
    fun setUp() {
        val dispatcher = StandardTestDispatcher()
        Dispatchers.setMain(dispatcher)
    }

    private fun component(): HomeComponent {
        return HomeComponentImpl()
    }
}