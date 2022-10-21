package io.wsl.exceptions

import io.wsl.handlers.Handler

class MoreThanOneDefaultHandlerSet(val foundDefaultHandlers: List<Handler>) : Exception()