package io.wsl.cfg

import io.wsl.GlobalConfigClass

interface GlobalConfigClassBeanProvider {
    fun provide(): GlobalConfigClass
}