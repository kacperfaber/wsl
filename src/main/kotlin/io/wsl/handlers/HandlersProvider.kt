package io.wsl.handlers

interface HandlersProvider {
    fun provideAll(packagePrefix: String): List<Handler>
}