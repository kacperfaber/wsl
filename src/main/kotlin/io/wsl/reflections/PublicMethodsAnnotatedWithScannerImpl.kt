package io.wsl.reflections

import org.springframework.stereotype.Component
import java.lang.reflect.Method

// TODO: Test PublicMethodsAnnotatedWithScannerImpl [:PublicMethodsAnnotatedWithScanner] bean.

@Component
class PublicMethodsAnnotatedWithScannerImpl(var isMethodAnnotatedWith: IsMethodAnnotatedWithChecker, var publicMethodsScanner: PublicMethodsScanner) : PublicMethodsAnnotatedWithScanner {
    override fun scan(clazz: Class<*>, annotationClass: Class<out Annotation>): List<Method>  {
        return publicMethodsScanner.scan(clazz).filter { isMethodAnnotatedWith.check(it, annotationClass) }
    }
}