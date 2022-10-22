@file:Suppress("DEPRECATION")

package io.wsl.org_reflections

import org.reflections.Reflections
import org.reflections.scanners.SubTypesScanner
import org.reflections.util.ConfigurationBuilder
import org.reflections.util.FilterBuilder
import org.springframework.stereotype.Component

@Component
class ReflectionsForPackageScanProviderImpl : ReflectionsForPackageScanProvider {
    override fun provide(packages: List<String>): Reflections {
        val filterBuilder = FilterBuilder()
        for (`package` in packages) {
            filterBuilder.includePackage(`package`)
        }

        return Reflections(ConfigurationBuilder().setScanners(SubTypesScanner(false)).forPackages(*packages.toTypedArray()).filterInputsBy(filterBuilder))
    }
}