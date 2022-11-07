package io.wsl.executor

interface ArrayValueProvider {
    fun provide(invokeParams: List<InvokeParameter>): Array<Any?>
}