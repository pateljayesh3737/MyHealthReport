package com.nsh.core.common.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val nshDispatchers: NshDispatchers)

enum class NshDispatchers {
    Default,
    IO,
}
