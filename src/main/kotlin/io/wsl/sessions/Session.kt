package io.wsl.sessions

import org.springframework.web.socket.WebSocketSession

class Session(var webSocketSession: WebSocketSession, var user: Any?)