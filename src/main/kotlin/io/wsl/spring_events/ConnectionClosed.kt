package io.wsl.spring_events

import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.WebSocketSession

class ConnectionClosed(val session: WebSocketSession, val closeStatus: CloseStatus)