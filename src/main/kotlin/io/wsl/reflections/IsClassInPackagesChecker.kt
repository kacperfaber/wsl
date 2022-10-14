package io.wsl.reflections

interface IsClassInPackagesChecker {
    fun check(clazz: Class<*>, packages: List<String>): Boolean
}