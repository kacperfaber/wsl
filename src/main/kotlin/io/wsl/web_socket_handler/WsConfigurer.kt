package io.wsl.web_socket_handler

import io.wsl.actions.ActionsByHandlerGrouper
import io.wsl.model.WslModel
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry

@Configuration
open class WsConfigurer(val actionsByHandlerGrouper: ActionsByHandlerGrouper, val handlerFactory: WebSocketHandlerFactory, val wslModel: WslModel) : WebSocketConfigurer {
    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        val actionsGrouped = actionsByHandlerGrouper.group(wslModel.actions, wslModel.controllers, wslModel.handlers)
        wslModel.handlers.forEach {
            val handler = handlerFactory.createHandler(actionsGrouped[it] ?: throw Exception("No Actions depends to handler " + it.clazz), it)
            registry.addHandler(handler, it.path).setAllowedOriginPatterns(*it.allowedOrigins)
        }
    }
}