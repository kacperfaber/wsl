package io.wsl.extensions

import io.wsl.action_call.ActionCall
import org.springframework.web.socket.WebSocketSession

abstract class PostExtension : ExtensionComponent() {
    abstract fun afterInvoke(actionCall: ActionCall, result: Any?, annotation: Annotation, session: WebSocketSession)
}