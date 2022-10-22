package io.wsl.model

import io.wsl.actions.ActionsFromAllControllersProvider
import io.wsl.actions.ActionsProvider
import io.wsl.cfg.ConfigProvider
import io.wsl.classlist.ClassListProvider
import io.wsl.controllers.ControllersByClassListProvider
import io.wsl.events.EventHandlerListProvider
import io.wsl.globalcfg.GlobalConfigClassBeanProvider
import io.wsl.handlers.*
import org.springframework.stereotype.Component

@Component
class DefaultWslModelProvider(var defaultHandlerProviderOrNull: DefaultHandlerProviderOrNull, var actionsFromAllControllersProvider: ActionsFromAllControllersProvider, var eventsProvider: EventHandlerListProvider, var actionsProvider: ActionsProvider, var controllersProvider: ControllersByClassListProvider, var classListProvider: ClassListProvider, var handlersProvider:HandlersByClassListProvider, var globalConfigClassBeanProvider: GlobalConfigClassBeanProvider, var configProvider: ConfigProvider) : WslModelProvider {
    override fun provide(): WslModel {
        val globalConfigClassBean = globalConfigClassBeanProvider.provide()
        val config = configProvider.provide(globalConfigClassBean.clazz)
        val classList = classListProvider.provide(config)
        val handlers = handlersProvider.provide(classList)
        val defaultHandler = defaultHandlerProviderOrNull.provide(handlers)
        val controllers = controllersProvider.provide(classList, handlers, defaultHandler)
        val allActions = actionsFromAllControllersProvider.provide(controllers)
        return WslModel(handlers, controllers, allActions)
    }
}