package io.wsl.classlist

import org.springframework.stereotype.Component

@Component
class ClassListFromClassesGeneratorImpl() : ClassListFromClassesGenerator {
    override fun generate(classes: Set<Class<*>>): ClassList {
        return ClassList(classes)
    }
}