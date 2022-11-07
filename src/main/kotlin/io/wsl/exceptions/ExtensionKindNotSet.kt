package io.wsl.exceptions

class ExtensionKindNotSet(extensionAnnotationClass: Class<out Annotation>) : Exception("Seems ${extensionAnnotationClass.name} not using @SetExtensionKind annotation.")