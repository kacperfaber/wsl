package io.wsl.extensions

import io.wsl.action_call.ActionCall
import org.springframework.web.socket.WebSocketSession

abstract class PreExtension : ExtensionComponent() {
    abstract fun beforeInvoke(actionCall: ActionCall, annotation: Annotation, session: WebSocketSession)
}