package io.wsl

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class Handler(val path: String, val allowedOrigins: Array<String> = ["*"])
