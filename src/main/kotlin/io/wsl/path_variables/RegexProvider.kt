package io.wsl.path_variables

interface RegexProvider {
    fun provide(actionNameOrPath: String): Regex
}