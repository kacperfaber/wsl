package io.wsl.reflections

import org.springframework.stereotype.Component
import java.lang.reflect.Method

@Component
class MethodAnnotationsProviderImpl : MethodAnnotationsProvider {
    override fun provide(method: Method): Array<Annotation> {
        return method.annotations
    }
}