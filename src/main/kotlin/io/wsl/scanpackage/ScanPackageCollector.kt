package io.wsl.scanpackage

interface ScanPackageCollector {
    fun collect(clazz: Class<*>, initialList: MutableList<String>): MutableList<String>
}