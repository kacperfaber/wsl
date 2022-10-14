package io.wsl.reflections

interface IsPackageNameInPrefixesChecker {
    fun check(packageName: String, prefixes: List<String>): Boolean
}