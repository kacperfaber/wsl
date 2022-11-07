package io.wsl.parameters

import io.wsl.extensions.ExtensionModel

class ActionParameter(
    var type: Class<*>,
    @Deprecated("Use list of extensions [var 'extensions']")
    var extension: ExtensionModel?,
    var extensions: List<ExtensionModel>
)