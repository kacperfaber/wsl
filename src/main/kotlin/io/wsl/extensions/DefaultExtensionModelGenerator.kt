package io.wsl.extensions

import org.springframework.stereotype.Component

@Component
class DefaultExtensionModelGenerator : ExtensionModelGenerator {
    override fun generate(componentClass: Class<out ExtensionComponent>, annotation: Annotation, extensionKind: ExtensionKind): ExtensionModel {
        return ExtensionModel(componentClass, annotation, extensionKind)
    }
}