package io.wsl.reflections

interface ClassByPackagesFilter {
    fun filter(packages: List<String>, classList: Collection<Class<*>>): List<Class<*>>
}