package io.wsl.invoke_parameter

import io.wsl.action_call.ActionCall
import io.wsl.parameters.ParameterList
import org.springframework.web.socket.WebSocketSession

interface InvokeParameterListProvider {
    fun collect(parameterList: ParameterList, actionCall: ActionCall, session: WebSocketSession): List<InvokeParameter>
}