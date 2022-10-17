package io.wsl.classlist

interface ClassListFromClassesGenerator {
    fun generate(classes: Set<Class<*>>): ClassList
}