package io.wsl.reflections

import org.springframework.stereotype.Component

@Component
class DefaultIsClassInPackageChecker(var packageNameProvider: ClassPackageNameProvider) : IsClassInPackageChecker {
    override fun check(clazz: Class<*>, packageString: String): Boolean {
        return packageNameProvider.provide(clazz) == packageString
    }
}