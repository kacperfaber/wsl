package io.wsl.reflections

import org.springframework.stereotype.Component

@Component
class ClassPackageNameProviderImpl : ClassPackageNameProvider {
    override fun provide(clazz: Class<*>): String {
        return clazz.packageName
    }
}