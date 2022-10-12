package io.wsl.tests

fun assertIsException(clazz: Class<*>) {
    assertIsAssignableFrom(Exception::class.java, clazz)
}