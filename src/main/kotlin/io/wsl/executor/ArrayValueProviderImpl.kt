package io.wsl.executor

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