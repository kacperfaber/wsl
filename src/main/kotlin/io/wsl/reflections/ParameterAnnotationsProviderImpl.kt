package io.wsl.reflections

import org.springframework.stereotype.Component
import java.lang.reflect.Parameter

@Component
class ParameterAnnotationsProviderImpl : ParameterAnnotationsProvider {
    override fun provide(parameter: Parameter): Array<Annotation> {
        return parameter.annotations
    }
}