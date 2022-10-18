package io.wsl.handlers

import io.wsl.classlist.ClassList

interface HandlersByClassListProvider {
    fun provide(classList: ClassList): List<Handler>
}