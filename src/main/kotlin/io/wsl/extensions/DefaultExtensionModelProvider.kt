package io.wsl.extensions

import io.wsl.exceptions.ExtensionKindNotSet
import org.springframework.stereotype.Component

@Component
class DefaultExtensionModelProvider(var generator: ExtensionModelGenerator, var extensionKindProvider: ExtensionKindValueProvider) : ExtensionModelProvider {
    override fun provide(annotation: Annotation, extensionComponentClass: Class<out ExtensionComponent>): ExtensionModel {
        val extensionKind = extensionKindProvider.provide(annotation.annotationClass.java) ?:
            throw ExtensionKindNotSet(annotation.annotationClass.java)
        return generator.generate(extensionComponentClass, annotation, extensionKind)
    }
}