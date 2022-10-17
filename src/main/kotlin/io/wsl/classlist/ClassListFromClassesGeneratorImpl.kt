package io.wsl.classlist

import org.springframework.stereotype.Component

@Component
class ClassListFromClassesGeneratorImpl(var emptyClassListGenerator: EmptyClassListGenerator) : ClassListFromClassesGenerator {
    override fun generate(classes: Set<Class<*>>): ClassList {
        val classList = emptyClassListGenerator.generate()
        for (`class` in classes) {
            classList.add(`class`)
        }
        return classList
    }
}