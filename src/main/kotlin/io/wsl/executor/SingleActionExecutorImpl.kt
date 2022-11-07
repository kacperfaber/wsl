package io.wsl.executor

import io.wsl.action_call.ActionCallProvider
import io.wsl.actions.Action
import io.wsl.reflections.MethodInvoker
import io.wsl.sessions.Session
import org.springframework.stereotype.Component

@Component
class SingleActionExecutorImpl(var valueProvider: ArrayValueProvider, var methodInvoker: MethodInvoker, var actionCallProvider: ActionCallProvider, var controllerInstanceBeanProvider: ControllerInstanceBeanProvider, var invokeParameterListProvider: InvokeParameterListProvider) : SingleActionExecutor {
    override fun execute(action: Action, session: Session, messageName: String, messageData: String): ExecutionResult {
        val controllerInstance = controllerInstanceBeanProvider.provide(action.controllerClass)
        val actionCall = actionCallProvider.provide(controllerInstance, action.method)
        val invokeParameters = invokeParameterListProvider.collect(action.parameterList, actionCall)

        // TODO: Make InvokeParameters validated.

        // TODO: Add PayloadExtension

        val values = valueProvider.provide(invokeParameters)
        val result = methodInvoker.invoke(action.method, controllerInstance, values) // TODO: No values.

        // TODO: Add ResultException

        TODO()
    }
}