package io.testproj

import io.wsl.*
import io.wsl.action_call.ActionCall
import io.wsl.actions.Action
import io.wsl.actions.ActionsByHandlerGrouper
import io.wsl.extensions.*
import io.wsl.model.WslModel
import io.wsl.web_socket_handler.WebSocketHandlerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.*
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.WebSocketMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry

@GlobalConfig
@ScanPackage("io.testproj")
class TestProject_GlobalConfig

@Handler("/chat", allowedOrigins = ["*", "localhost"])
class TestProject_ChatHandler

@Component
class TestProject_ReturnStringComponent() : PostExtension() {
    override fun afterInvoke(actionCall: ActionCall, result: Any?, annotation: Annotation, session: WebSocketSession) {
        println("Hello World! - User says: " + result!! as String)

    }
}

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@SetComponent(componentClass = TestProject_ParamComp::class)
@SetExtensionKind(ExtensionKind.ActionParameter)
annotation class TestProject_Kacperek

@Component
class TestProject_ParamComp : ParameterExtension() {
    override fun getValue(actionCall: ActionCall, parameterType: Class<*>, annotation: Annotation): Any? {
        return "Kacperek<3"
    }
}

@Controller
class HttpController(var model: WslModel) {
    @RequestMapping("/test", method = [RequestMethod.GET])
    fun test(): ResponseEntity<String> {
        return ResponseEntity.ok("test")
    }
}

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@SetComponent(TestProject_ReturnStringComponent::class)
@SetExtensionKind(ExtensionKind.PostAction)
annotation class TestProject_ReturnString

@SocketController
@Component
@SetHandler(TestProject_ChatHandler::class)
class TestProject_ChatController {
    @SocketAction("sh")
    @TestProject_ReturnString
    fun sayHello(@TestProject_Kacperek name: String): String {
        println("Resolved name $name")
        return "Hello haha it works.!"
    }
}

@Configuration
@EnableWebSocket
open class TestProject_Configurer(val actionsByHandlerGrouper: ActionsByHandlerGrouper, val handlerFactory: WebSocketHandlerFactory, val wslModel: WslModel) : WebSocketConfigurer {
    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        val actionsGrouped = actionsByHandlerGrouper.group(wslModel.actions, wslModel.controllers, wslModel.handlers)
        println("\n\n\n\n\n\n\nTo register we have ${wslModel.handlers.count()} handlers.\n\n\n")
        wslModel.handlers.forEach {
            val handler = handlerFactory.createHandler(actionsGrouped[it] ?: throw Exception("No Actions depends to handler " + it.clazz), it)
            registry.addHandler(handler, it.path).setAllowedOriginPatterns(*it.allowedOrigins)
        }
    }
}

@Configuration
@Component
@Controller
@Import(WslSpringConfig::class)
@ComponentScan("io.wsl.web_socket_handler")
open class TestProject_Config {
    @Bean
    open fun classes(): GlobalConfigClass {
        return GlobalConfigClass(TestProject_GlobalConfig::class.java)
    }
}

@SpringBootApplication(scanBasePackages = ["io.testproj", "io.wsl.*", "io.wsl"])
@Configuration
@EnableWebSocket
open class Main



fun main(args: Array<String>) {
    runApplication<Main>(*args)
}