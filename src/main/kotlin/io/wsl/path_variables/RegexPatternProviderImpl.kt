package io.wsl.path_variables

import org.springframework.stereotype.Component

@Component
class RegexPatternProviderImpl(var pathDeclarationToRegexConverter: PathVariablePathDeclarationToRegexConverter) : RegexPatternProvider {
    override fun provide(nameOrPath: String): String {
        return nameOrPath.replace(Regex("\\{(.+):(.+)}")) {
            return@replace pathDeclarationToRegexConverter.convert(it.groups[1]!!.value, it.groups[2]!!.value)
        }
    }
}