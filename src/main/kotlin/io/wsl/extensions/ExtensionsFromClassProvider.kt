package io.wsl.extensions

interface ExtensionsFromClassProvider {
    fun provide(clazz: Class<*>): List<ExtensionModel>
}