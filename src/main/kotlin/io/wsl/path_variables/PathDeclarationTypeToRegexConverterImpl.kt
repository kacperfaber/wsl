package io.wsl.path_variables

import org.springframework.stereotype.Component

@Component
class PathDeclarationTypeToRegexConverterImpl : PathDeclarationTypeToRegexConverter {
    override fun convert(type: String): String {
        return when (type.lowercase()) {
            VariableDeclarationTypes.INT -> "\\+?\\-?\\d+"
            VariableDeclarationTypes.POSITIVE_INT -> "\\+?\\d+"
            VariableDeclarationTypes.NEGATIVE_INT -> "-\\d+"
            VariableDeclarationTypes.BOOLEAN -> "true|false"
            VariableDeclarationTypes.BINARY -> "0|1"
            VariableDeclarationTypes.STRING -> ".+"
            VariableDeclarationTypes.FLOAT -> "[0-9\\.]+"
            else -> throw UnresolvedPathVariableType()
        }
    }
}