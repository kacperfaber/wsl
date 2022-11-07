package io.wsl.invoke_parameter

import io.wsl.action_call.ActionCall
import io.wsl.extensions.ParameterExtension
import io.wsl.extensions.SingleActionParameterExtensionProvider
import io.wsl.listBuilder
import io.wsl.parameter_extension_executor.ParameterExtensionExecutor
import io.wsl.parameters.ParameterList
import org.springframework.stereotype.Component

@Component
class InvokeParameterListProviderImpl(var invokeParameterProvider: InvokeParameterProvider, var singleActionParameterExtensionProvider: SingleActionParameterExtensionProvider, var parameterExtensionExecutor: ParameterExtensionExecutor) :
    InvokeParameterListProvider {
    override fun collect(parameterList: ParameterList, actionCall: ActionCall): List<InvokeParameter> = listBuilder {
        val parametersSize = parameterList.size
        for (parameterIndex in 0..parametersSize) {
            val parameter = parameterList[parameterIndex]

            // TODO: There's a filter, GC deletes the list after firstOrNull be picked.
            val extension = singleActionParameterExtensionProvider.provide(parameter.extensions)

            if (extension != null) {
                @Suppress("UNCHECKED_CAST")
                val componentClass = extension.componentClass as Class<out ParameterExtension>
                val extensionResult = parameterExtensionExecutor.execute(
                    actionCall, parameter.type,
                    componentClass, extension.annotation
                )
                yield(invokeParameterProvider.provide(parameter.type, extensionResult, parameter.extensions))
            }

            else {
                TODO("Sorry but actually only parameters value source it's from extensions.")
            }
        }
    }
}