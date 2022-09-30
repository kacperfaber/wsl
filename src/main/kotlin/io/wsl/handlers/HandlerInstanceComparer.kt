package io.wsl.handlers

interface HandlerInstanceComparer {
    fun compare(handler1: Handler, handler2: Handler): Boolean
}