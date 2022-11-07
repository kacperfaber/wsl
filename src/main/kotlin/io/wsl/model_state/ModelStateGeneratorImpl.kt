package io.wsl.model_state

import org.springframework.stereotype.Component

@Component
class ModelStateGeneratorImpl : ModelStateGenerator {
    override fun generate(): ModelState {
        return ModelState(mutableListOf())
    }
}