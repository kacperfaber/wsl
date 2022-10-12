package io.wsl.globalcfg

import io.wsl.extensions.ExtensionsFromClassProvider
import io.wsl.scanpackage.ScanPackageCollector
import io.wsl.scanprefix.ScanPrefixCollector
import org.springframework.stereotype.Component

@Component
class GlobalConfigProviderImpl(var scanPrefixCollector: ScanPrefixCollector, var scanPackageCollector: ScanPackageCollector, var extensionsFromClassProvider: ExtensionsFromClassProvider) : GlobalConfigProvider {
    override fun provide(clazz: Class<*>): GlobalConfig {
        val extensions = extensionsFromClassProvider.provide(clazz)
        val prefixes = scanPrefixCollector.collect(clazz, mutableListOf())
        val packages = scanPackageCollector.collect(clazz, mutableListOf())
        return GlobalConfig(clazz, packages, prefixes, extensions)
    }
}