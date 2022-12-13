package com.nikitakrapo.analytics.firebase

/**
 * Checks Firebase Events naming convention
 *
 * TODO: think if it's mandatory as It takes some milliseconds to process each event
 *
 * [Firebase doc](https://developers.google.com/android/reference/com/google/firebase/analytics/FirebaseAnalytics.Event)
 */
fun checkEventName(event: String) {
    val length = event.length
    require(length in 1..40) {
        "Event name {$event} has illegal length"
    }

    val containsIllegalCharacters = event.any { !it.isLetter() && it != '_' }
    require(!containsIllegalCharacters) {
        "Event name {$event} contains illegal characters"
    }

    val startsWithAlphabetic = event.first().isLetter()
    require(startsWithAlphabetic) {
        "Event name {$event} starts with non-alphabetic character"
    }

    val hasIllegalPrefix = RESERVED_PREFIXES.any { event.startsWith(it) }
    require(!hasIllegalPrefix) {
        "Event name {$event} starts with illegal prefix"
    }

    val isReservedEvent = RESERVED_EVENTS.any { event == it }
    require(!isReservedEvent) {
        "Event name {$event} is reserved"
    }
}

internal val RESERVED_PREFIXES = listOf(
    "firebase_",
    "google_",
    "ga_",
)

internal val RESERVED_EVENTS = listOf(
    "ad_activeview",
    "ad_click",
    "ad_exposure",
    "ad_impression",
    "ad_query",
    "ad_reward",
    "adunit_exposure",
    "app_background",
    "app_clear_data",
    "app_exception",
    "app_remove",
    "app_store_refund",
    "app_store_subscription_cancel",
    "app_store_subscription_convert",
    "app_store_subscription_renew",
    "app_update",
    "app_upgrade",
    "dynamic_link_app_open",
    "dynamic_link_app_update",
    "dynamic_link_first_open",
    "error",
    "first_open",
    "first_visit",
    "in_app_purchase",
    "notification_dismiss",
    "notification_foreground",
    "notification_open",
    "notification_receive",
    "os_update",
    "session_start",
    "session_start_with_rollout",
    "user_engagement",
)