package io.wsl.scanprefix

interface ScanPrefixCollector {
    fun collect(clazz: Class<*>, initialList: MutableList<String>): MutableList<String>
}