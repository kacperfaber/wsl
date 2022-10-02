package io.wsl.actions

import io.wsl.SocketAction
import java.lang.reflect.Method

interface SocketActionAnnotationProvider {
    fun provide(method: Method): SocketAction?
}