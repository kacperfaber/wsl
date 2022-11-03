package io.wsl.exceptions

class NoParameterRegistered(val type: Class<*>) : Exception(message = "No instance assignable to '${type.name}' registered.")