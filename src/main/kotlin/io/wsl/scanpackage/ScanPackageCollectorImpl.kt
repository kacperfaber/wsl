package io.wsl.scanpackage

import io.wsl.ScanPackage
import io.wsl.listBuilderFrom
import io.wsl.reflections.SpecifiedAnnotationsByClassFromClassProvider
import org.springframework.stereotype.Component

@Component
class ScanPackageCollectorImpl(var specifiedAnnotationsByClassFromClassProvider: SpecifiedAnnotationsByClassFromClassProvider) : ScanPackageCollector {
    override fun collect(clazz: Class<*>, initialList: MutableList<String>): MutableList<String> = listBuilderFrom(initialList) {
        val scanPackages = specifiedAnnotationsByClassFromClassProvider.provide(clazz, ScanPackage::class.java)
        for (scanPackage in scanPackages) {
            yieldAll(*scanPackage.packages)
        }
    }
}