package io.wsl.cfg

interface ConfigProvider {
    fun provide(globalConfigClass: Class<*>): Config
}