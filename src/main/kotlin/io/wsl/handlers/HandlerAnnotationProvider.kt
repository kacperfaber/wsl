package io.wsl.handlers

import io.wsl.Handler

interface HandlerAnnotationProvider {
    fun provide(clazz: Class<*>): Handler?
}