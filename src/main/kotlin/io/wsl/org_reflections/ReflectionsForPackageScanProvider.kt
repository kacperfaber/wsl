package io.wsl.org_reflections

import org.reflections.Reflections

interface ReflectionsForPackageScanProvider {
    fun provide(packages: List<String>): Reflections
}