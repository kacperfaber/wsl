package io.wsl.reflections

import org.springframework.stereotype.Component

@Component
class DefaultClassByPackagesFilter(var isClassInPackagesChecker: IsClassInPackagesChecker) : ClassByPackagesFilter {
    override fun filter(packages: List<String>, classList: Collection<Class<*>>): List<Class<*>> {
        return classList.filter { isClassInPackagesChecker.check(it, packages) }
    }
}