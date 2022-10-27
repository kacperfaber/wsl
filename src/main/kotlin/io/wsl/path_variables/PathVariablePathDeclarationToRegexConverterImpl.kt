package io.wsl.path_variables

import org.springframework.stereotype.Component

@Component
class PathVariablePathDeclarationToRegexConverterImpl(var pathDeclarationTypeToRegexConverter: PathDeclarationTypeToRegexConverter) : PathVariablePathDeclarationToRegexConverter {
    override fun convert(name: String, type: String): String {
        val typeConverted = pathDeclarationTypeToRegexConverter.convert(type)
        return "(?'$name'$typeConverted)"
    }
}