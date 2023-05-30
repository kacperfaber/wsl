package io.wsl.reflections

import org.springframework.stereotype.Component
import java.lang.reflect.Method

@Component
class MethodInvokerImpl : MethodInvoker {
    override fun invoke(method: Method, instance: Any, vararg params: Any?): Any? {
        return method.invoke(instance, *params)
    }
}