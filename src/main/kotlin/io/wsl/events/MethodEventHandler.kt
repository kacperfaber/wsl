package io.wsl.events

import java.lang.reflect.Method

class MethodEventHandler(key: Class<*>, var componentClass: Class<*>, var method: Method) : BaseEventHandler(key)