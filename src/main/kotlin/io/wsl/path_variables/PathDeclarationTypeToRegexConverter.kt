package io.wsl.path_variables

interface PathDeclarationTypeToRegexConverter {
    fun convert(type: String): String
}