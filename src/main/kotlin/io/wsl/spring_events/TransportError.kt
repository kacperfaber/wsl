package io.wsl.spring_events

import org.springframework.web.socket.WebSocketSession

class TransportError(val session: WebSocketSession, val throwable: Throwable)