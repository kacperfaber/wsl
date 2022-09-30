package io.wsl.handlers

import io.wsl.Handler
import io.wsl.reflections.GenericAnnotationProvider
import org.springframework.stereotype.Component

@Component
class HandlerAnnotationProviderImpl(var genericAnnotationProvider: GenericAnnotationProvider) : HandlerAnnotationProvider {
    override fun provide(clazz: Class<*>): Handler? {
        return genericAnnotationProvider.provide(clazz, Handler::class.java)
    }
}