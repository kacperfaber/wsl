package io.wsl.globalcfg

interface GlobalConfigProvider {
    fun provide(clazz: Class<*>): GlobalConfig
}