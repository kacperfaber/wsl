package io.wsl.controllers

import io.wsl.classlist.ClassList
import io.wsl.handlers.Handler

interface ControllersByClassListProvider {
    fun provide(classList: ClassList, handlers: List<Handler>, defaultHandler: Handler?): List<Controller>
}