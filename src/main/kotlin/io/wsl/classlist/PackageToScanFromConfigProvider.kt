package io.wsl.classlist

import io.wsl.cfg.Config

interface PackageToScanFromConfigProvider {
    fun provide(config: Config): List<String>
}