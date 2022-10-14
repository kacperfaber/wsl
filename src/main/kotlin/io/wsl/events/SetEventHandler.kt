package io.wsl.events

import kotlin.reflect.KClass

@Target(AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class SetEventHandler(val clazz: KClass<*>)
