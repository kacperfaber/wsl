package io.wsl.globalcfg

import io.wsl.GlobalConfigClass

interface GlobalConfigClassBeanProvider {
    fun provide(): GlobalConfigClass
}