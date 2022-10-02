package io.wsl.actions

import io.wsl.SocketAction
import io.wsl.reflections.MethodGenericAnnotationProvider
import org.springframework.stereotype.Component
import java.lang.reflect.Method

@Component
class SocketActionAnnotationProviderImpl(var genericAnnotationProvider: MethodGenericAnnotationProvider) : SocketActionAnnotationProvider {
    override fun provide(method: Method): SocketAction? {
        return genericAnnotationProvider.provide(method, SocketAction::class.java)
    }
}