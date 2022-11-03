package io.wsl.extensions

import io.wsl.action_call.ActionCall

abstract class ParameterExtension : ExtensionComponent() {
    abstract fun getValue(actionCall: ActionCall, parameterType: Class<*>, annotation: Annotation): Any?
}