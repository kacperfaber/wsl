package io.wsl.extensions

import io.wsl.action_call.ActionCall

abstract class PreExtension : ExtensionComponent() {
    abstract fun beforeInvoke(actionCall: ActionCall, annotation: Annotation)
}