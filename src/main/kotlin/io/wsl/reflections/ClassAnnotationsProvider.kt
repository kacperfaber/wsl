package io.wsl.reflections

interface ClassAnnotationsProvider {
    fun provide(clazz: Class<*>): Array<Annotation>
}