package io.wsl.model

import io.wsl.GlobalConfigClass
import org.springframework.context.annotation.Condition
import org.springframework.context.annotation.ConditionContext
import org.springframework.core.type.AnnotatedTypeMetadata
import org.springframework.stereotype.Component

@Component
class GlobalConfigClassBeanRegistered : Condition {
    override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
        return try {context.beanFactory?.getBean(GlobalConfigClass::class.java) != null} catch (e: Exception) {false}
    }
}