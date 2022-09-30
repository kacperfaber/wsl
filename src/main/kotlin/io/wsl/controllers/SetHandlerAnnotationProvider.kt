package io.wsl.controllers

import io.wsl.SetHandler

interface SetHandlerAnnotationProvider {
    fun provide(clazz: Class<*>): SetHandler?
}