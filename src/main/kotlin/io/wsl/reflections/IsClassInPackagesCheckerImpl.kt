package io.wsl.reflections

import org.springframework.stereotype.Component

@Component
class IsClassInPackagesCheckerImpl(var packageNameProvider: ClassPackageNameProvider) : IsClassInPackagesChecker {
    override fun check(clazz: Class<*>, packages: List<String>): Boolean {
        val packageName = packageNameProvider.provide(clazz)
        return packages.any { it == packageName }
    }
}