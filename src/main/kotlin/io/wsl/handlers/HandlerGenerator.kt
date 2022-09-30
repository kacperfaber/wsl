package io.wsl.handlers

interface HandlerGenerator {
    fun generate(path: String, allowedOrigins: Array<String>, isDefault: Boolean): Handler
}