package io.wsl.tests

import kotlin.test.fail

fun assertIsAssignableFrom(clazz: Class<*>, assignableFrom: Class<*>) {
    if (!clazz.isAssignableFrom(assignableFrom))
        fail("Class ${clazz.name} is not assignable from ${assignableFrom.name}")
}