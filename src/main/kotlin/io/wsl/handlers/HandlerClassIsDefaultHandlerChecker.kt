package io.wsl.handlers

interface HandlerClassIsDefaultHandlerChecker {
    fun check(clazz: Class<*>): Boolean
}