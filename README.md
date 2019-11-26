# Spring 5 Webflux + reactive websocket + custom message producer
This sample project is intended to illustrate how to set up spring 5 webflux, reactive websocket, thymeleaf and a websocket message producer.

It uses the following dependencies:
- Spring boot 2.2.1.RELEASE
- spring-boot-starter-webflux
- spring-boot-starter-thymeleaf

Important configuration are:

ReactiveWebSocketConfiguration class
- Provide websocket handler mapping bean defining an uri to receive connections.
- Provide a handle adapter.

ReactiveWebSocketHandler class
- Implement WebSocketHandler interface providing an implementation to the handle method. Its implementation receives an WebSocketSession that will subscribe to a custom publisher (StatusEmitter) that will produce messages to the clients. Also handle method prepares WebSocketSession to receive clients messages just to log them.

StatusEmitter class
- Make use of a FluxProcessor to produce and emmit content.
- Through DirectProcessor, creates a Flux where consumers will receive messages emmited only after the subscription. All prior messages will be discarded what is exactly what I needed when I had created this sample project.

index.html page
- A sample configuration using javascript to perform the websocket connection, receive its messages and update a html element to display the content received.
- Important to note the use of window.onbeforeunload event responsible to close websocket connection avoiding unused sessions on server side.
