package io.wsl.classlist

import io.wsl.cfg.Config
import io.wsl.org_reflections.ClassesProvider
import io.wsl.org_reflections.ReflectionsForPackageScanProvider
import org.springframework.stereotype.Component

@Component
class ClassListProviderImpl(var classListFromClassesGenerator: ClassListFromClassesGenerator, var classesProvider: ClassesProvider, var packageToScanFromConfigProvider: PackageToScanFromConfigProvider, var reflectionsProvider: ReflectionsForPackageScanProvider) : ClassListProvider {
    override fun provide(config: Config): ClassList {
        val packages = packageToScanFromConfigProvider.provide(config)
        val reflections = reflectionsProvider.provide(packages)
        val classes = classesProvider.provide(reflections)

        // TODO: Make ClassList with property 'Set<Class>' is performance-better.

        return classListFromClassesGenerator.generate(classes)
    }
}