package io.wsl.pre_extensions_executor

import io.wsl.BeanOrNullProvider
import io.wsl.action_call.ActionCall
import io.wsl.exceptions.ExtensionComponentNotRegistered
import io.wsl.extensions.ExtensionModel
import io.wsl.extensions.PreExtension
import org.springframework.stereotype.Component

@Component
class PreExtensionsExecutorImpl(var beanOrNullProvider: BeanOrNullProvider) : PreExtensionsExecutor {

    @Suppress("UNCHECKED_CAST")
    override fun execute(onlyPreExtensions: List<ExtensionModel>, actionCall: ActionCall) {
        onlyPreExtensions.forEach {
            val bean = beanOrNullProvider.provide(it.componentClass as Class<out PreExtension>)
                ?: throw ExtensionComponentNotRegistered()

            bean.beforeInvoke(actionCall, it.annotation)
        }
    }
}