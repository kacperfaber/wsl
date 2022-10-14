package io.wsl.events

import io.wsl.listBuilder
import io.wsl.reflections.PublicMethodsScanner
import org.springframework.stereotype.Component

@Component
class EventHandlerListProviderImpl(var methodEventHandlerGenerator: MethodEventHandlerGenerator, var publicMethodsScanner: PublicMethodsScanner, var methodEventHandlerKeyProvider: MethodEventHandlerKeyProvider) : EventHandlerListProvider {
    override fun provide(includedClass: List<Class<*>>): List<BaseEventHandler> = listBuilder {
        for (includeClass in includedClass) {
            val publicMethods = publicMethodsScanner.scan(includeClass)
            for (publicMethod in publicMethods) {
                val key = methodEventHandlerKeyProvider.check(publicMethod) ?: continue
                val eventHandler = methodEventHandlerGenerator.generate(key, includeClass, publicMethod)
                yield(eventHandler)
            }
        }
    }
}