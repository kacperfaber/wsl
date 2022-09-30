package io.wsl

interface ArrayToListConverter {
    fun <T> convert(arr: Array<T>): List<T>
}