package io.wsl.tests

import java.lang.reflect.Method

class WithMethodClass {
    fun notAnnotated() {}

    @MethodAnnotation
    fun annotated() {}

    companion object {
        fun annotatedMethod(): Method {
            return WithMethodClass::class.java.getMethod("annotated")
        }

        fun notAnnotated(): Method {
            return WithMethodClass::class.java.getMethod("notAnnotated")
        }
    }
}