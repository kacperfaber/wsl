package io.wsl.controllers

import io.wsl.extensions.ExtensionModel
import io.wsl.handlers.Handler

class Controller(var clazz: Class<*>, var handler: Handler?, var extensions: List<ExtensionModel>)