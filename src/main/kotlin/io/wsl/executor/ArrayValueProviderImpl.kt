package io.wsl.executor

import io.wsl.invoke_parameter.InvokeParameter
import org.springframework.stereotype.Component

@Component
class ArrayValueProviderImpl : ArrayValueProvider {
    override fun provide(invokeParams: List<InvokeParameter>): Array<Any?> {
        val argSize = invokeParams.size
        val arr = arrayOfNulls<Any?>(argSize)

        // TODO: Convert to classic for, I made it like that because it's 19:00

        invokeParams.forEachIndexed {
            index, invokeParam -> arr[index] = invokeParams[index].value
        }

        return arr
    }
}