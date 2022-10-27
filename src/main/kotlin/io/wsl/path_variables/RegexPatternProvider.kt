package io.wsl.path_variables

interface RegexPatternProvider {
    fun provide(nameOrPath: String): String
}