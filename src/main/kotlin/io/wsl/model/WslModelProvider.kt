package io.wsl.model

interface WslModelProvider {
    fun provide(): WslModel
}