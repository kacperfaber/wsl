package io.wsl.post_extensions_executor

import io.wsl.action_call.ActionCall
import io.wsl.extensions.ExtensionModel

interface PostExtensionsExecutor {
    fun execute(onlyPostExtensions: List<ExtensionModel>, actionCall: ActionCall, result: Any?)
}