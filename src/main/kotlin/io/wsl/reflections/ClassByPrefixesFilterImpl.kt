package io.wsl.reflections

import org.springframework.stereotype.Component

@Component
class ClassByPrefixesFilterImpl(var packageNameProvider: ClassPackageNameProvider, var isPackageNameInPrefixesChecker: IsPackageNameInPrefixesChecker) : ClassByPrefixesFilter {
    override fun filter(classList: List<Class<*>>, prefixes: List<String>): List<Class<*>> {
        return classList.filter {
            val packageName = packageNameProvider.provide(it)
            isPackageNameInPrefixesChecker.check(packageName, prefixes)
        }
    }
}