package com.websocket.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;

import reactor.core.publisher.Mono;

@Component("ReactiveWebSocketHandler")
public class ReactiveWebSocketHandler implements WebSocketHandler {

	private Logger logger = LoggerFactory.getLogger(ReactiveWebSocketHandler.class);

	@Autowired
	private StatusEmitter emitter;

    @Override
    public Mono<Void> handle(WebSocketSession webSocketSession) {
        return webSocketSession.send(emitter.publisher()
          .map(webSocketSession::textMessage))
          .doOnSubscribe(sig -> logger.info("Joinning web socket... sessionId: " + webSocketSession.getId()))
          .and(webSocketSession.receive()
            .map(WebSocketMessage::getPayloadAsText).log()
            .doFinally(sig -> logger.info("Leaving web socket... sessionId: " + webSocketSession.getId())));
    }
}
