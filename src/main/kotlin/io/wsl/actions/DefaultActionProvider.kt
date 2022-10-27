package io.wsl.actions

import io.wsl.ArrayToListConverter
import io.wsl.SocketAction
import io.wsl.controllers.Controller
import io.wsl.extensions.ExtensionModelsProvider
import io.wsl.parameters.ParameterListProvider
import io.wsl.path_variables.RegexProvider
import io.wsl.reflections.MethodAnnotationsProvider
import org.springframework.stereotype.Component
import java.lang.reflect.Method

@Component
class DefaultActionProvider(var regexProvider: RegexProvider, var arrayToListConverter: ArrayToListConverter, var annotationsProvider: MethodAnnotationsProvider, var extensionModelsProvider: ExtensionModelsProvider, var actionGenerator: ActionGenerator, var parameterListProvider: ParameterListProvider) : ActionProvider {
    override fun provide(method: Method, socketAction: SocketAction, controller: Controller): Action {
        val parameterList = parameterListProvider.provide(method)
        val annotationList = arrayToListConverter.convert(annotationsProvider.provide(method))
        val extensions = extensionModelsProvider.provide(annotationList)
        val pattern = regexProvider.provide(socketAction.name)
        return actionGenerator.generate(socketAction.name, method, controller.clazz, parameterList, extensions, pattern)
    }
}