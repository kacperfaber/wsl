package io.wsl.model_state

class ModelState(private val errors: MutableList<ModelError>) {
    val isValid
        get() = errors.isEmpty()

    fun addError(err: ModelError) = errors.add(err)
}