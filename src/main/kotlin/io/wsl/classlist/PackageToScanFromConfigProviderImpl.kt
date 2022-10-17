package io.wsl.classlist

import io.wsl.cfg.Config
import org.springframework.stereotype.Component

@Component
class PackageToScanFromConfigProviderImpl : PackageToScanFromConfigProvider {
    override fun provide(config: Config): List<String> {
        // TODO: Make Config implement interfaces like 'ScanPackagesHolder' etc...
        return config.scanPackages
    }
}