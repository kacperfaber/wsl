package io.wsl.executor

import io.wsl.action_call.ActionCall
import io.wsl.parameters.ParameterList

interface ParameterValuesCollector {
    fun collect(parameterList: ParameterList, actionCall: ActionCall): Array<Any?>
}