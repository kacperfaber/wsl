package io.wsl.extensions

import org.springframework.stereotype.Component

@Component
class DefaultExtensionModelProvider(var generator: ExtensionModelGenerator) : ExtensionModelProvider {
    override fun provide(annotation: Annotation, extensionComponentClass: Class<out ExtensionComponent>): ExtensionModel {
        return generator.generate(extensionComponentClass, annotation)
    }
}