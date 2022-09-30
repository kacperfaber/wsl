package io.wsl.controllers

import org.springframework.stereotype.Component

@Component
class DefaultSetHandlerValueProvider(var setHandlerAnnotationProvider: SetHandlerAnnotationProvider) : SetHandlerValueProvider {
    override fun provide(controllerClass: Class<*>): Class<*>? {
        return setHandlerAnnotationProvider.provide(controllerClass)?.clazz?.java
    }
}