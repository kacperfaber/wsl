package io.wsl.append

interface AppendCollector {
    fun collect(clazz: Class<*>): List<Class<*>>
}