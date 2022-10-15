package io.wsl.org_reflections

import org.reflections.Reflections
import org.reflections.util.ConfigurationBuilder
import org.springframework.stereotype.Component

@Component
class ReflectionsForPackageScanProviderImpl : ReflectionsForPackageScanProvider {
    override fun provide(packages: List<String>): Reflections {
        return Reflections(ConfigurationBuilder().forPackages(*packages.toTypedArray()))
    }
}