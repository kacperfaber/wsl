package io.wsl.events

interface SetEventHandlerAnnotationProvider {
    fun provide(annotations: Array<Annotation>): SetEventHandler?
}