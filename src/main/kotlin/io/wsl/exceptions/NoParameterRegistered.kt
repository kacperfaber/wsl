package io.wsl.exceptions

class NoParameterRegistered(val type: Class<*>) : Exception("No instance assignable to '${type.name}' registered.")