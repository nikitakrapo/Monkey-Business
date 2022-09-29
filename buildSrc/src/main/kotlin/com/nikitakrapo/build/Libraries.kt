package com.nikitakrapo.build

object KotlinLib {
    const val serialization = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"
    const val datetime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.datetime}"
}

object CoroutinesLib {
    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
}

object KtorLib {
    const val core = "io.ktor:ktor-client-core:${Versions.ktor}"
    const val logging = "io.ktor:ktor-client-logging:${Versions.ktor}"
    const val okHttp = "io.ktor:ktor-client-okhttp:${Versions.ktor}"
    const val darwin = "io.ktor:ktor-client-darwin:${Versions.ktor}"
}

object LogLib {
    const val napier = "io.github.aakira:napier:${Versions.napier}"
}