package io.wsl.actions

import io.wsl.extensions.ExtensionModel
import io.wsl.parameters.ParameterList
import org.springframework.stereotype.Component
import java.lang.reflect.Method

@Component
class ActionGeneratorImpl : ActionGenerator {
    override fun generate(
        name: String,
        method: Method,
        controllerClass: Class<*>,
        parameterList: ParameterList,
        extensions: List<ExtensionModel>,
        pattern: Regex
    ): Action {
        return Action(method, controllerClass, name, extensions, parameterList, pattern)
    }

}