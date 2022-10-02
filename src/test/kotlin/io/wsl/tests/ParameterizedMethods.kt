@file:Suppress("unused")

package io.wsl.tests

import java.lang.reflect.Method

@Suppress("UNUSED_PARAMETER")
class ParameterizedMethods {
    fun singleIntParam(x: Int) {}

    companion object {
        fun getSingleIntParamMethod(): Method {
            return ParameterizedMethods::class.java.getMethod("singleIntParam")
        }
    }
}