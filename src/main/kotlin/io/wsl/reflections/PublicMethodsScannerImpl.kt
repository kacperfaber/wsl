package io.wsl.reflections

import org.springframework.stereotype.Component
import java.lang.reflect.Method
import java.lang.reflect.Modifier

@Component
class PublicMethodsScannerImpl : PublicMethodsScanner {
    override fun scan(clazz: Class<*>): List<Method> {
        return clazz.methods.filter { !Modifier.isStatic(it.modifiers) }
    }
}