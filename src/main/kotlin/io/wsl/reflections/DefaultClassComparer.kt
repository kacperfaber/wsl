package io.wsl.reflections

import org.springframework.stereotype.Component

@Component
class DefaultClassComparer : ClassComparer {
    override fun compare(clazz1: Class<*>, clazz2: Class<*>): Boolean = clazz1 == clazz2
}