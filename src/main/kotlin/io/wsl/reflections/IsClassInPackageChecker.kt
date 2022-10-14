package io.wsl.reflections

interface IsClassInPackageChecker {
    fun check(clazz: Class<*>, packageString: String): Boolean
}