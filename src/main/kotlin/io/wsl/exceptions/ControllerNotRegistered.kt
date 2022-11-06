package io.wsl.exceptions

class ControllerNotRegistered(var controllerClass: Class<*>) : Exception("Couldn't find controller by class '${controllerClass.name}'")