package io.wsl.extensions

interface ExtensionsFromClassProvider {
    fun provide(clazz: Class<*>): List<ExtensionModel>
    fun provide(clazz: Class<*>, initialList: MutableList<ExtensionModel>): MutableList<ExtensionModel>
}