package io.wsl.executor

import io.wsl.action_call.ActionCallProvider
import io.wsl.actions.Action
import io.wsl.reflections.MethodInvoker
import io.wsl.sessions.Session
import org.springframework.stereotype.Component

@Component
class SingleActionExecutorImpl(var methodInvoker: MethodInvoker, var actionCallProvider: ActionCallProvider, var controllerInstanceBeanProvider: ControllerInstanceBeanProvider, var parameterValuesCollector: ParameterValuesCollector) : SingleActionExecutor {
    override fun execute(action: Action, session: Session, messageName: String, messageData: String): ExecutionResult {
        val controllerInstance = controllerInstanceBeanProvider.provide(action.controllerClass)
        val actionCall = actionCallProvider.provide(controllerInstance, action.method)
        val values = parameterValuesCollector.collect(action.parameterList, actionCall)

        // TODO: Add PayloadExtension

        val result = methodInvoker.invoke(action.method, controllerInstance, *values)

        // TODO: Add ResultException

        TODO()
    }
}