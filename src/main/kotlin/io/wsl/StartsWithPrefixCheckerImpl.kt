package io.wsl

import org.springframework.stereotype.Component

@Component
class StartsWithPrefixCheckerImpl : StartsWithPrefixChecker {
    override fun check(str: String, prefix: String): Boolean {
        return str.startsWith(prefix)
    }
}