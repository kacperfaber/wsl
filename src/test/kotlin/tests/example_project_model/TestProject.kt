package tests.example_project_model

import io.wsl.*
import io.wsl.extensions.ExtensionComponent
import io.wsl.extensions.SetComponent
import io.wsl.model.WslModelProvider
import io.wsl.tests.SpringTests
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Handler("/chat", ["*"])
class ChatHandler

@GlobalConfig
@AddUser
@ScanPackage("tests.example_project_model")
class Global

@SocketController
@Component
@SetHandler(ChatHandler::class)
class ChatController {
    @SocketAction("ping")
    fun ping() {

    }

    @SocketAction("pong")
    fun pong() {

    }
}

@Component
class AddUserComponent : ExtensionComponent()

@SetComponent(AddUserComponent::class)
annotation class AddUser

@SpringTests
@TestConfiguration
open class TestProject {
    @Autowired
    lateinit var wslModelProvider: WslModelProvider

    @Test
    fun `test XD`() {
        val model = wslModelProvider.provide()
    }

    @Bean
    open fun classes(): GlobalConfigClass = GlobalConfigClass(Global::class.java)
}

