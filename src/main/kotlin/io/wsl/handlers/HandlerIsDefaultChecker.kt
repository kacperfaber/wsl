package io.wsl.handlers

interface HandlerIsDefaultChecker {
    fun check(handler: Handler): Boolean
}