package io.wsl.tests

import io.wsl.EventHandler

class EventHandlerAnnotation {
    @EventHandler(EventHandlerAnnotation::class)
    fun method1() {}

    @EventHandler(TestComponent::class)
    fun method2() {}

    companion object {
        val INSTANCE = EventHandlerAnnotation()

        fun getInstance1(): EventHandler {
            return INSTANCE.javaClass.methods.first { it.name == "method1" }.getAnnotation(EventHandler::class.java)
        }

        fun getInstance2(): EventHandler {
            return INSTANCE.javaClass.methods.first { it.name == "method2" }.getAnnotation(EventHandler::class.java)
        }
    }
}