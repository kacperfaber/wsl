# wsl

[![build](https://github.com/kacperfaber/wsl/actions/workflows/build.yml/badge.svg)](https://github.com/kacperfaber/wsl/actions/workflows/build.yml)
[![test](https://github.com/kacperfaber/wsl/actions/workflows/test.yml/badge.svg)](https://github.com/kacperfaber/wsl/actions/workflows/test.yml)

## Installation 

> Actually not in maven repository...

### Installing (using Gradle)

```groovy

// build.gradle
implementation "io.wsl:wsl:1.0.0-beta.1"

// build.gradle.kts
implementation("io.wsl:wsl:1.0.0-beta.1")

```

### Installing (using Maven)

```xml

<dependency>
    <groupId>io.wsl</groupId>
    <artifactId>wsl</artifactId>
    <version>1.0.0-beta.1</version>
</dependency>

```

## Tutorial

#### 1. Create GlobalConfig
Create a class annotated with ```@GlobalConfig``` annotation. 
You can define a prefix to scan using `@ScanPackage(prefix="")`

```kotlin
@GlobalConfig
@ScanPackage("io.testproj")
class MyGlobalConfig
```

##### 1.1 Create GlobalConfigClasses bean
```kotlin
@Bean
open fun classes(): GlobalConfigClass {
    return GlobalConfigClass(MyGlobalConfig::class.java)
}
```

#### 2. Create a handler
Handler is a representation of endpoint. In **WSL** you can create many handlers, with many separated actions.

```kotlin
// this handler will be used with connection
// to /chat endpoint.

@Handler("/chat", allowedOrigins = ["*", "localhost"])
class ChatHandler 
```

#### 3. Create controllers
Controllers in **WSL** are like in HTTP framework but they have no prefixes. **SocketController**'s have no prefixes, but contain an actions.
Generally it's a spring components, so we can use all the spring container features we want.

```kotlin
@SocketController
@Component
@SetHandler() // see 4.
class ChatController {
    @SocketAction("print_hello")
    fun sayHello() {
        println("Hello")
    }
}
```

> Remember to use **@Component** from spring, or any other way to register this object in spring container.

#### 4. Assign controller to handler
All controllers must be assigned to handler. They're 2 ways to do this...

##### 4.1. Using @SetHandler

```kotlin
// will assign ExampleController to ChatHandler
@SetHandler(ChatHandler::class)
@SocketController
@Component
class ExampleController
```

##### 4.2 Using @DefaultHandler
**@DefaultHandler** annotation present on **Handler** will set the handler is default, and will be used when **Controller** has not @SetHandler annotation.

```kotlin
@Handler(/* ... */)
@DefaultHandler
class ChatHandler
```

##### 4.3 Exceptions

```kotlin
package io.wsl.exceptions

// When DefaultHandler is wanted, but not set.
// is wanted when the @SetHandler not set.
class DefaultHandlerNotSet

// Something wrong with the handler assigned.
class HandlerNotResolved
```

#### 5. Create extensions

**WSL** has nothing to use, but you can get awesome results using extensions. 
Extensions are spring components related with annotations, **WSL** is calling extensions when message is received and action is picked.

##### 5.1 PreExtension

Extension built to invoke before action (**@SocketAction**) is invoked.

```kotlin
@SetComponent(UseBodyComponent::class)
@SetExtensionKing(ExtensionKind.PreAction)
annotation class UseBody

@Component
class UseBodyComponent : PreExtension() {
    override fun beforeInvoke(actionCall: ActionCall, annotation: Annotation, session: WebSocketSession) {
        val body = Json().parse<Body>(actionCall.messageData)
        actionCall.parameters[Body::class.java] = body
    }
}
```

Usage:

```kotlin
@SocketController
class ChatController { 
    @SocketAction("print_body")
    @UseBody
    fun printBody(body: Body) {
        println(body)
    }
    
    // Our component will add optional instance before request,
    // which wsl use unless method takes.
    
    // In this scenario we read the data from the message,
    // But we can do many things.
}
```

##### 5.2 PostExtension
Post Extensions will be invoked after the action (Method) is called.
So, we can use what this method returned, and for example return this to the user.

```kotlin
@SetComponent(ReturnComponent::class)
@SetExtensionKing(ExtensionKind.PostAction)
annotation class Returns(val produces: String)

class ReturnsComponent() : PostExtension() {
    override fun afterInvoke(actionCall: ActionCall, result: Any?, annotation: Annotation, session: WebSocketSession) {
        // we're using annotation instance to get some data. 
        val produces = (annotation as Returns).produces 
        
        val responseBody = Json().write(result)
        
        // send message to the user.
        session.send(TextMessage("$produces: $responseBody"))
    }
}
```

Usage:
```kotlin
@SocketController
class ChatController { 
    @SocketAction("my_name")
    @Returns("your_name_is")
    fun whatsMyName(): String = "Kacper"
    
    // Now this action will respond with "Kacper" always.
    // And this message will be send
    // to user who sent message 'my_name'
}
```

> Remember, the built-in way to send messages is dependency MessagingService ( ***from io.wsl.messages.messaging*** ).
> 
> You can access this just like normal bean.
> 

##### 5.3 ParameterExtension
Extension we use in parameters, these kinda extensions only set the property before action is invoked.

```kotlin
@SetComponent(UseBodyComponent::class)
@SetExtensionKing(ExtensionKind.ActionParameter)
annotation class PrincipalId

@Component
class PrincipalIdComponent : ParameterExtension() {
    override fun getValue(actionCall: ActionCall, parameterType: Class<*>, annotation: Annotation, session: WebSocketSession): Any? {
        // We're using WebSocketSession instance to get principal.name
        return session.getPrincipal().getName()
    }
}
```

Usage:
```kotlin
@SocketController
class ChatController {
    @SocketAction("who_am_i")
    fun whoAmI(@PrincipalId name: String) {
        // `name` is equal to principal id.
    }
    
}
```

##### 5.4 Inheritance

**WSL** applies **PostExtension**s and **PreExtensions** to lower floors of WSL structure.

1. **GlobalConfig**: 
Extension applied in GlobalConfig will be in all **WSL** structure.

2. **Handler**: Extension applied in Handler will be used in all controllers [then in actions], in whole handler.

3. **SocketController**: Will apply extension to all SocketAction`s in this controller.

Some example wrote in free style

```kotlin

@GlobalConfig
@A
class Global 

    @Handler
    @B
    // @A: ChatHandler derives @A extension from GlobalConfig
    class ChatHandler

        @SocketController
        @D
        // @A: From Global
        // @B: From ChatHandler
        class ChatController 

            @SocketAction
            @E
            // @A: From GlobalConfig
            // @B: From ChatHandler
            fun sendMessage()
            
            // Finally: we have:
            // @A, @B, @D, @E

    @Handler
    @C
    // @A: ChatHandler derives @A extension from GlobalConfig
    class UserHandler

        @SocketController
        @Z
        // @C: LoginController derives @A extension from GlobalConfig
        // @C: LoginController derives @C extension from UserHandler
        class LoginController

        // Finally: All actions under LoginController have @A, @C, @Z

```

#### 6. Register handlers

```kotlin
// Will simplify this in update.

@Configuration
@EnableWebSocket
@Import(WslSpringConfig::class)
class ProjectConfigurer(val actionsByHandlerGrouper: ActionsByHandlerGrouper, val handlerFactory: WebSocketHandlerFactory, val wslModel: WslModel) : WebSocketConfigurer {
    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        val actionsGrouped = actionsByHandlerGrouper.group(wslModel.actions, wslModel.controllers, wslModel.handlers)
        wslModel.handlers.forEach {
            val handler = handlerFactory.createHandler(actionsGrouped[it] ?: throw Exception("No Actions depends to handler " + it.clazz), it)
            registry.addHandler(handler, it.path).setAllowedOriginPatterns(*it.allowedOrigins)
        }
    }
}
```

## 7. Warning

It's just an beta version, please report all issues.
