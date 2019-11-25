package com.websocket.demo.tester;

import java.util.Arrays;

import com.websocket.demo.StatusEmitter;

import reactor.core.publisher.Flux;

public class Tester {

	public static void main(String[] args) {
	    StatusEmitter statusEmitter = new StatusEmitter();
	    statusEmitter.publisher().subscribe(x -> System.out.println(x));
	    Flux.fromIterable(Arrays.asList("INIT", "DONE")).subscribe(x -> 
	        statusEmitter.publishStatus(x));
	}
}
