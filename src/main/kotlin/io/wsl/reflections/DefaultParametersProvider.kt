package io.wsl.reflections

import org.springframework.stereotype.Component
import java.lang.reflect.Method
import java.lang.reflect.Parameter

@Component
class DefaultParametersProvider : ParametersProvider {
    override fun provide(method: Method): Array<Parameter> {
        return method.parameters
    }
}