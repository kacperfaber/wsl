package io.wsl.events

interface SetEventHandlerValueProvider {
    fun provide(annotations: Array<Annotation>): Class<*>?
}