package io.testproj

import io.wsl.*
import io.wsl.action_call.ActionCall
import io.wsl.actions.Action
import io.wsl.actions.ActionsByHandlerGrouper
import io.wsl.extensions.*
import io.wsl.json.ObjectJsonDeserializer
import io.wsl.json.ObjectJsonSerializer
import io.wsl.messages.messaging.MessagingService
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
class TestProject_ReturnStringComponent(private val messagingService: MessagingService) : PostExtension() {
    override fun afterInvoke(actionCall: ActionCall, result: Any?, annotation: Annotation, session: WebSocketSession) {
        println("Hello World! - User says: " + result!! as String)
        messagingService.send(session, "user-says", result.toString())
    }
}

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@SetComponent(componentClass = TestProject_ParamComp::class)
@SetExtensionKind(ExtensionKind.ActionParameter)
annotation class TestProject_Kacperek

@Component
class TestProject_ParamComp : ParameterExtension() {
    override fun getValue(actionCall: ActionCall, parameterType: Class<*>, annotation: Annotation, session: WebSocketSession): Any? {
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

class ShPayload {
    lateinit var name: String
}

@Component
class TestProject_GetPropComponent(private val objectJsonDeserializer: ObjectJsonDeserializer) : ParameterExtension() {
    override fun getValue(
        actionCall: ActionCall,
        parameterType: Class<*>,
        annotation: Annotation,
        session: WebSocketSession
    ): Any? {
        return objectJsonDeserializer.deserialize(actionCall.messageData, ShPayload::class.java).name
    }
}

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@SetComponent(TestProject_GetPropComponent::class)
@SetExtensionKind(ExtensionKind.ActionParameter)
annotation class TestProject_GetProp(val property: String)

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@SetComponent(TestProject_ReturnStringComponent::class)
@SetExtensionKind(ExtensionKind.PostAction)
annotation class TestProject_ReturnString

@Component
class TestProject_ReturnJsonComp(val objectSerializer: ObjectJsonSerializer, val messagingService: MessagingService) : PostExtension() {
    override fun afterInvoke(actionCall: ActionCall, result: Any?, annotation: Annotation, session: WebSocketSession) {
        messagingService.send(session, (annotation as TestProject_ReturnJson).produces, result)
    }
}

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@SetComponent(TestProject_ReturnJsonComp::class)
@SetExtensionKind(ExtensionKind.PostAction)
annotation class TestProject_ReturnJson(val produces: String)

@SocketController
@Component
@SetHandler(TestProject_ChatHandler::class)
class TestProject_ChatController {
    @SocketAction("sh")
    @TestProject_ReturnJson("sh_response")
    fun sayHello(@TestProject_Kacperek name: String, @TestProject_GetProp("name") bodyName: String): Map<String, String> {
        println("Resolved name $name from parameter extension")
        println("From body we got $bodyName")
        return mapOf("from_ext" to name, "from_body" to bodyName)
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