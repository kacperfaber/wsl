package io.wsl.reflections

import io.wsl.StartsWithPrefixChecker
import org.springframework.stereotype.Component

@Component
class IsPackageNameInPrefixesCheckerImpl(var startsWithPrefixChecker: StartsWithPrefixChecker) : IsPackageNameInPrefixesChecker {
    override fun check(packageName: String, prefixes: List<String>): Boolean {
        return prefixes.any {
            startsWithPrefixChecker.check(packageName, it)
        }
    }
}