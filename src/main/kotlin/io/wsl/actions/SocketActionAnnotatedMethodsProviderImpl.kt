package io.wsl.actions

import io.wsl.SocketAction
import io.wsl.reflections.PublicMethodsAnnotatedWithScanner
import org.springframework.stereotype.Component
import java.lang.reflect.Method

@Component
class SocketActionAnnotatedMethodsProviderImpl(var publicMethodsAnnotatedWithScanner: PublicMethodsAnnotatedWithScanner) : SocketActionAnnotatedMethodsProvider {
    override fun provide(clazz: Class<*>): List<Method> {
        return publicMethodsAnnotatedWithScanner.scan(clazz, SocketAction::class.java)
    }
}