package io.wsl.reflections

interface ClassPackageNameProvider {
    fun provide(clazz: Class<*>): String
}