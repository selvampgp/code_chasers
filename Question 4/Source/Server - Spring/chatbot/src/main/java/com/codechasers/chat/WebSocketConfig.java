package com.codechasers.chat;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**To configuring message handling with simple messaging protocols (STOMP) from WebSocket clients.
 * Typically used to customize the configuration provided via @EnableWebSocketMessageBroker.
 *
 */
@Configuration
@EnableWebSocketMessageBroker
@ComponentScan(basePackages = "com.kgisl.chat.controller")
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	    @Override
	    public void configureMessageBroker(MessageBrokerRegistry config) {
	        config.enableSimpleBroker("/user");
	        config.setApplicationDestinationPrefixes("/app");
	    }
	 
	    @Override
	    public void registerStompEndpoints(StompEndpointRegistry registry) {
	         registry.addEndpoint("user", "bot").setAllowedOrigins("*").
	         withSockJS();
	    }

}
