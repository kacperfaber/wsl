package io.wsl.controllers

import io.wsl.handlers.Handler

interface ControllersProvider {
    fun provideAll(packagePrefix: String, handlers: List<Handler>, defaultHandler: Handler?): List<Controller>
}