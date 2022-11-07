package io.wsl.executor

import io.wsl.invoke_parameter.InvokeParameter

interface ArrayValueProvider {
    fun provide(invokeParams: List<InvokeParameter>): Array<Any?>
}