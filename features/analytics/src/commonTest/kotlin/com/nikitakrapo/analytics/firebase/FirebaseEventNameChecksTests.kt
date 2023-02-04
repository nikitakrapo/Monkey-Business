package com.nikitakrapo.analytics.firebase

import kotlin.test.Test
import kotlin.test.assertFails

class FirebaseEventNameChecksTests {

    @Test
    fun empty_fails() {
        val event = ""

        assertFails { checkEventName(event) }
    }

    @Test
    fun tooLong_fails() {
        val event = buildString {
            for (i in 0 until 41) append("a")
        }

        assertFails { checkEventName(event) }
    }

    @Test
    fun illegalCharacters_fails() {
        val event = "sample.event"

        assertFails { checkEventName(event) }
    }

    @Test
    fun startsWithUnderscore_fails() {
        val event = "_sample"

        assertFails { checkEventName(event) }
    }

    @Test
    fun startsWithNonAlphabetic_fails() {
        val event = ".sample"

        assertFails { checkEventName(event) }
    }

    @Test
    fun reservedPrefix_fails() {
        RESERVED_PREFIXES.forEach { prefix ->
            val event = "${prefix}sample"

            assertFails { checkEventName(event) }
        }
    }

    @Test
    fun reservedEvent_fails() {
        RESERVED_EVENTS.forEach { event ->
            assertFails { checkEventName(event) }
        }
    }

    @Test
    fun correctEvent_checks() {
        val event = "sample_event"
        checkEventName(event)
    }

    @Test
    fun correctEventStartingWithUppercase_checks() {
        val event = "Sample_event"
        checkEventName(event)
    }

    @Test
    fun correctUppercaseEvent_checks() {
        val event = "SAMPLE_EVENT"
        checkEventName(event)
    }

    @Test
    fun correctShortEvent_checks() {
        val event = "a"
        checkEventName(event)
    }

    @Test
    fun correctLongEvent_checks() {
        val event = buildString {
            for (i in 0 until 40) append("a")
        }
        checkEventName(event)
    }
}
