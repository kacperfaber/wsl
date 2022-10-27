package io.wsl.path_variables

import org.springframework.stereotype.Component

@Component
class RegexProviderImpl(var regexPatternProvider: RegexPatternProvider) : RegexProvider {
    override fun provide(actionNameOrPath: String): Regex {
        return Regex(regexPatternProvider.provide(actionNameOrPath))
    }
}