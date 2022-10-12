package io.wsl

interface BeanProvider {
    fun <T> provide(clazz: Class<T>): T
}