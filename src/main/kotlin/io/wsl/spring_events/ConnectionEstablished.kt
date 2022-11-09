package io.wsl.spring_events

import org.springframework.web.socket.WebSocketSession

class ConnectionEstablished(val session: WebSocketSession)