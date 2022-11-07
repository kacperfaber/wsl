package io.wsl.extensions

@Target(AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class SetExtensionKind(val extensionKind: ExtensionKind)
