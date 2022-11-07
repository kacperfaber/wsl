package io.wsl.extensions

import io.wsl.action_call.ActionCall

abstract class PostExtension : ExtensionComponent() {
    abstract fun afterInvoke(actionCall: ActionCall, result: Any?, annotation: Annotation)
}