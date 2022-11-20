package io.wsl.tests

import io.wsl.Handler

@Handler("/chat", ["*"])
class HandlerAnnotatedClass {
    companion object {
        fun handlerInstance(): Handler {
            return HandlerAnnotatedClass::class.java.annotations.first {it.annotationClass.java == Handler::class.java} as Handler
        }
    }
}