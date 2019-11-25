package com.websocket.demo;

import org.springframework.stereotype.Service;

import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxProcessor;
import reactor.core.publisher.FluxSink;

@Service
public class StatusEmitter {

	private final FluxProcessor<String, String> processor;
	private final FluxSink<String> sink;
	
	public StatusEmitter() {
		this.processor = DirectProcessor.<String>create().serialize();
		this.sink = processor.sink();
	}
	
	public Flux<String> publisher() {
		return this.processor.map(x -> x);
	}
	
	public void publishStatus(String status) {
		sink.next(status);
	}

}
