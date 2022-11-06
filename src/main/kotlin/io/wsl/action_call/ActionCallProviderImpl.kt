package io.wsl.action_call

import org.springframework.stereotype.Component
import java.lang.reflect.Method

@Component
class ActionCallProviderImpl : ActionCallProvider {
    override fun provide(controllerInstance: Any, method: Method): ActionCall {
        return ActionCall(mapOf(), method, controllerInstance)
    }
}