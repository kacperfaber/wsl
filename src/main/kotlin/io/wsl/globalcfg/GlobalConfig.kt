package io.wsl.globalcfg

import io.wsl.extensions.ExtensionModel

class GlobalConfig(
    var clazz: Class<*>,
    var append: List<Class<*>>,
    var scanPackage: List<String>,
    var scanPrefix: List<String>,
    var extensions: List<ExtensionModel>
)