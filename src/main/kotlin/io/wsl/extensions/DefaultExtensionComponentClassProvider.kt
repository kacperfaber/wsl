package io.wsl.extensions

import org.springframework.stereotype.Component

@Component
class DefaultExtensionComponentClassProvider : ExtensionComponentClassProvider {
    override fun provide(setComponent: SetComponent): Class<out ExtensionComponent> {
        return setComponent.componentClass.java
    }
}