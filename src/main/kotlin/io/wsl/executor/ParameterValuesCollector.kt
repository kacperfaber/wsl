package io.wsl.executor

import io.wsl.action_call.ActionCall
import io.wsl.parameters.ParameterList

interface ParameterValuesCollector {
    fun collect(controllerInstance: Any, parameterList: ParameterList, actionCall: ActionCall): Array<Any?>
}