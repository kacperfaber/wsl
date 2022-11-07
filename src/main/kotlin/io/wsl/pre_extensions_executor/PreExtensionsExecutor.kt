package io.wsl.pre_extensions_executor

import io.wsl.action_call.ActionCall
import io.wsl.extensions.ExtensionModel

interface PreExtensionsExecutor {
    fun execute(onlyPreExtensions: List<ExtensionModel>, actionCall: ActionCall)
}