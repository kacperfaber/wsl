package io.wsl.tests

import io.wsl.SocketAction
import java.lang.reflect.Method

class SampleSocketAnnotations {
    @SocketAction("ping")
    fun ping() {}

    companion object {
        fun pingMethod(): Method {
            return SampleSocketAnnotations::class.java.methods.first { it.name == "ping" }
        }
    }
}
