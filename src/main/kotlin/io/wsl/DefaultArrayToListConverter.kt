package io.wsl

import org.springframework.stereotype.Component

@Component
class DefaultArrayToListConverter : ArrayToListConverter {
    override fun <T> convert(arr: Array<T>): List<T> {
        return arr.toList()
    }
}