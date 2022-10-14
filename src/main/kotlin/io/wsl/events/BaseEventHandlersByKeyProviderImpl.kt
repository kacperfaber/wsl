package io.wsl.events

import org.springframework.stereotype.Component

@Component
class BaseEventHandlersByKeyProviderImpl : BaseEventHandlersByKeyProvider {
    override fun provide(key: Class<*>): List<BaseEventHandler> {
         TODO("No Model")
    }
}