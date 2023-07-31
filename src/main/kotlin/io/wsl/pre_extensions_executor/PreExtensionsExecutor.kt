package io.wsl.pre_extensions_executor

import io.wsl.action_call.ActionCall
import io.wsl.extensions.ExtensionModel
import org.springframework.web.socket.WebSocketSession

interface PreExtensionsExecutor {
    fun execute(onlyPreExtensions: List<ExtensionModel>, actionCall: ActionCall, session: WebSocketSession)
}