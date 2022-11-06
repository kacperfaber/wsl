package io.wsl.executor

interface ControllerInstanceBeanProvider {
    fun provide(controllerClass: Class<*>): Any
}