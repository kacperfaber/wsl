package io.wsl.spring_events

import org.springframework.web.socket.WebSocketSession

class MessageReceived(val text: String, val session: WebSocketSession)