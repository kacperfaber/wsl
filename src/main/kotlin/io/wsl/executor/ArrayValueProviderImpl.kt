package io.wsl.executor

import io.wsl.invoke_parameter.InvokeParameter
import org.springframework.stereotype.Component

@Component
class ArrayValueProviderImpl : ArrayValueProvider {
    override fun provide(invokeParams: List<InvokeParameter>): Array<Any?> {
        val argSize = invokeParams.size
        val arr = arrayOfNulls<Any?>(argSize)
        for (i in 0..argSize) {
            arr[i] = invokeParams[i].value
        }
        return arr
    }
}