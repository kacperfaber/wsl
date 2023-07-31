package io.wsl.executor

import io.wsl.action_call.ActionCallProvider
import io.wsl.actions.Action
import io.wsl.extensions.ExtensionKind
import io.wsl.extensions.ExtensionModelsByExtensionKindGrouper
import io.wsl.invoke_parameter.InvokeParameterListProvider
import io.wsl.invoke_parameter.InvokeParametersValidator
import io.wsl.model_state.ModelStateGenerator
import io.wsl.post_extensions_executor.PostExtensionsExecutor
import io.wsl.pre_extensions_executor.PreExtensionsExecutor
import io.wsl.reflections.MethodInvoker
import org.springframework.stereotype.Component
import org.springframework.web.socket.WebSocketSession

@Component
class SingleActionExecutorImpl(
    var preExtensionsExecutor: PreExtensionsExecutor,
    var extensionsGrouper: ExtensionModelsByExtensionKindGrouper,
    var postExtensionsExecutor: PostExtensionsExecutor,
    var modelStateGenerator: ModelStateGenerator,
    var invokeParametersValidator: InvokeParametersValidator,
    var valueProvider: ArrayValueProvider,
    var methodInvoker: MethodInvoker,
    var actionCallProvider: ActionCallProvider,
    var controllerInstanceBeanProvider: ControllerInstanceBeanProvider,
    var invokeParameterListProvider: InvokeParameterListProvider
) : SingleActionExecutor {
    override fun execute(action: Action, session: WebSocketSession, messageName: String, messageData: String): ExecutionResult {
        val controllerInstance = controllerInstanceBeanProvider.provide(action.controllerClass)
        val actionCall = actionCallProvider.provide(controllerInstance, action.method, messageName, messageData)
        val invokeParameters = invokeParameterListProvider.collect(action.parameterList, actionCall, session)
        val modelState = modelStateGenerator.generate()

        invokeParametersValidator.doValidation(invokeParameters, modelState)

        val extensionsGrouped = extensionsGrouper.group(action.extensions)

        if (extensionsGrouped[ExtensionKind.PreAction] != null) {
            preExtensionsExecutor.execute(extensionsGrouped[ExtensionKind.PreAction]!!, actionCall, session)
        }

        val values = valueProvider.provide(invokeParameters)
        val result = methodInvoker.invoke(action.method, controllerInstance, *values)

        if (extensionsGrouped[ExtensionKind.PostAction] != null) {
            postExtensionsExecutor.execute(extensionsGrouped[ExtensionKind.PostAction]!!, actionCall, result, session)
        }

        // TODO: I have an idea - Some actions requires no errors, some will be called with errors. If there's more than one action to invoke,
        // TODO: We can skip to next, when in this ModelState there are errors.
        return ExecutionResult.CONTINUE
    }
}