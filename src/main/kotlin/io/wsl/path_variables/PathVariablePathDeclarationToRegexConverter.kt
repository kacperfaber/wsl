package io.wsl.path_variables

interface PathVariablePathDeclarationToRegexConverter {
    fun convert(name: String, type: String): String
}