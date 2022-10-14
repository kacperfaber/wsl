package io.wsl.reflections

interface ClassNameProvider {
    fun provide(clazz: Class<*>): String
}