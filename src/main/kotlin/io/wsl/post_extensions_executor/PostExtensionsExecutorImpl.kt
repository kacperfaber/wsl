package io.wsl.post_extensions_executor

import io.wsl.BeanOrNullProvider
import io.wsl.action_call.ActionCall
import io.wsl.exceptions.ExtensionComponentNotRegistered
import io.wsl.extensions.ExtensionModel
import io.wsl.extensions.PostExtension
import org.springframework.stereotype.Component
import org.springframework.web.socket.WebSocketSession

@Component
class PostExtensionsExecutorImpl(var beanProvider: BeanOrNullProvider) : PostExtensionsExecutor {
    @Suppress("UNCHECKED_CAST")
    override fun execute(onlyPostExtensions: List<ExtensionModel>, actionCall: ActionCall, result: Any?, session: WebSocketSession) {
        for (postExtension in onlyPostExtensions) {
            val bean = beanProvider.provide(postExtension.componentClass as Class<out PostExtension>)
                ?: throw ExtensionComponentNotRegistered()

            bean.afterInvoke(actionCall, result, postExtension.annotation, session)
        }
    }
}