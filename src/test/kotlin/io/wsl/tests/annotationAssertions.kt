package io.wsl.tests

import kotlin.reflect.KClass
import kotlin.test.fail

fun Class<out Annotation>.assertOnlyTargetIs(target: AnnotationTarget) {
    assertOnlyAnnotationTargetIs(this, target)
}

fun assertOnlyAnnotationTargetIs(annotationClass: Class<out Annotation>, target: AnnotationTarget) {
    val targets = annotationClass.getAnnotation(Target::class.java)?.allowedTargets
    if (targets != null && targets.count() == 1 && targets.first() == target)
        return
    fail("Only annotation target had to be $target, but it's not.")
}

fun assertAnnotationRetentionIs(annotationClass: Class<out Annotation>, retention: AnnotationRetention) {
    val value = annotationClass.getAnnotation(Retention::class.java)?.value
    if (value != retention) fail("Retention had to be $retention, but it's not.")
}

fun Class<out Annotation>.assertRetentionIsRuntime() {
    assertAnnotationRetentionIs(this, AnnotationRetention.RUNTIME)
}

fun assertIsAnnotationClass(clazz: Class<out Annotation>) {
    if (!clazz.isAnnotation) fail("Class had to be annotation class, but it's not.")
}