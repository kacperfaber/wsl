package io.wsl.executor

import io.wsl.action_call.ActionCall
import io.wsl.extensions.ParameterExtension
import io.wsl.extensions.SingleActionParameterExtensionProvider
import io.wsl.parameter_extension_executor.ParameterExtensionExecutor
import io.wsl.parameters.ParameterList
import org.springframework.stereotype.Component

@Component
class ParameterValuesCollectorImpl(var singleActionParameterExtensionProvider: SingleActionParameterExtensionProvider, var parameterExtensionExecutor: ParameterExtensionExecutor) : ParameterValuesCollector {
    override fun collect(parameterList: ParameterList, actionCall: ActionCall): Array<Any?> {
        val parametersSize = parameterList.size
        val array = arrayOfNulls<Any?>(parametersSize)
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
                array[parameterIndex] = extensionResult
            }

            else {
                TODO("Sorry but actually only parameters value source it's from extensions.")
            }
        }
        return array
    }
}