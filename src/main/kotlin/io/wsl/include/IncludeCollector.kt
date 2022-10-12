package io.wsl.include

interface IncludeCollector {
    fun collect(clazz: Class<*>): List<Class<*>>
}