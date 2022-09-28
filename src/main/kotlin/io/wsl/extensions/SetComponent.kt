package io.wsl.extensions

import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class SetComponent(val componentClass: KClass<out ExtensionComponent>)
