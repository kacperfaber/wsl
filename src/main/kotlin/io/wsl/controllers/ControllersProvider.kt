package io.wsl.controllers

interface ControllersProvider {
    fun provideAll(packagePrefix: String): List<Controller>
}