package io.wsl.model

import io.wsl.actions.Action
import io.wsl.controllers.Controller
import io.wsl.handlers.Handler

class WslModel(var handlers: List<Handler>, var controllers: List<Controller>, var actions: List<Action>)