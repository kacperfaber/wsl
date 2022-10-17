package io.wsl.classlist

import io.wsl.cfg.Config

interface ClassListProvider {
    fun provide(config: Config): ClassList
}