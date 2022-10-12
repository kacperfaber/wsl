package io.wsl

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
annotation class ScanPrefix(vararg val prefixes: String)
