package io.wsl.reflections

interface ClassComparer {
    fun compare(clazz1: Class<*>, clazz2: Class<*>): Boolean
}