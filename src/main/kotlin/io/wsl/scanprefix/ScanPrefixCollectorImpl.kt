package io.wsl.scanprefix

import io.wsl.ScanPrefix
import io.wsl.listBuilderFrom
import io.wsl.reflections.SpecifiedAnnotationsByClassFromClassProviderImpl
import org.springframework.stereotype.Component

@Component
class ScanPrefixCollectorImpl(var specifiedAnnotationsByClassFromClassProviderImpl: SpecifiedAnnotationsByClassFromClassProviderImpl) : ScanPrefixCollector {
    override fun collect(clazz: Class<*>, initialList: MutableList<String>): MutableList<String> = listBuilderFrom(initialList) {
        val scanPackages = specifiedAnnotationsByClassFromClassProviderImpl.provide(clazz, ScanPrefix::class.java)
        for (scanPackage in scanPackages) {
            yieldAll(*scanPackage.prefixes)
        }
    }
}