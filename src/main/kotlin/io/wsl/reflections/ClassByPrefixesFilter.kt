package io.wsl.reflections

interface ClassByPrefixesFilter {
    fun filter(classList: List<Class<*>>, prefixes: List<String>): List<Class<*>>
}