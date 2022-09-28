package io.wsl.extensions

interface ExtensionComponentClassProvider {
    fun provide(setComponent: SetComponent): Class<out ExtensionComponent>
}