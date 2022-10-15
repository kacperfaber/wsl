package io.wsl.classlist

import org.springframework.stereotype.Component

@Component
class EmptyClassListGeneratorImpl : EmptyClassListGenerator {
    override fun generate(): ClassList {
        return ClassList()
    }
}