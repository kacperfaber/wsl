package io.wsl.parameters

import io.wsl.reflections.ParametersProvider
import org.springframework.stereotype.Component
import java.lang.reflect.Method

@Component
class ParameterListProviderImpl(var actionParameterProvider: ActionParameterProvider, var parametersProvider: ParametersProvider) : ParameterListProvider {
    override fun provide(method: Method): ParameterList {
        val list = ParameterList()
        val params = parametersProvider.provide(method)

        for (param in params) {
            val actionParameter = actionParameterProvider.provide(param)
            list.add(actionParameter)
        }

        return list
    }

}