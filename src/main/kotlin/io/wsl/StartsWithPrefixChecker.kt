package io.wsl

interface StartsWithPrefixChecker {
    fun check(str: String, prefix: String): Boolean
}