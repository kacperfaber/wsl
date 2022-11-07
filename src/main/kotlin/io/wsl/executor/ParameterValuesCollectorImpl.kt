package io.wsl.executor

import io.wsl.action_call.ActionCall
import io.wsl.extensions.ParameterExtension
import io.wsl.parameter_extension_executor.ParameterExtensionExecutor
import io.wsl.parameters.ParameterList
import org.springframework.stereotype.Component

@Component
class ParameterValuesCollectorImpl(var parameterExtensionExecutor: ParameterExtensionExecutor) : ParameterValuesCollector {
    override fun collect(parameterList: ParameterList, actionCall: ActionCall): Array<Any?> {
        val parametersSize = parameterList.size
        val array = arrayOfNulls<Any?>(parametersSize)
        for (parameterIndex in 0..parametersSize) {
            val parameter = parameterList[parameterIndex]
            // TODO: Here, we getting value from ParameterExtension. What if they're more than one extension to get the value?
            if (parameter.extension != null) {
                val extension = parameter.extension!!
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