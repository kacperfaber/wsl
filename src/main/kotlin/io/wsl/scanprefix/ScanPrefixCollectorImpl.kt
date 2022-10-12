package io.wsl.scanprefix

import io.wsl.ScanPrefix
import io.wsl.listBuilderFrom
import io.wsl.reflections.SpecifiedAnnotationsByClassFromClassProvider
import org.springframework.stereotype.Component

@Component
class ScanPrefixCollectorImpl(var specifiedAnnotationsByClassFromClassProvider: SpecifiedAnnotationsByClassFromClassProvider) : ScanPrefixCollector {
    override fun collect(clazz: Class<*>, initialList: MutableList<String>): MutableList<String> = listBuilderFrom(initialList) {
        val scanPackages = specifiedAnnotationsByClassFromClassProvider.provide(clazz, ScanPrefix::class.java)
        for (scanPackage in scanPackages) {
            yieldAll(*scanPackage.prefixes)
        }
    }
}