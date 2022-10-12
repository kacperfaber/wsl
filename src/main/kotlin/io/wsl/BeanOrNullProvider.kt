package io.wsl

interface BeanOrNullProvider {
    fun <T> provide(clazz: Class<T>): T?
}