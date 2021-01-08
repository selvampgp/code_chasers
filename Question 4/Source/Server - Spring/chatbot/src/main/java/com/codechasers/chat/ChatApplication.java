package com.codechasers.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/** Chatbot application that can simulate a conversation with a user in NL through messaging applications or websites
 * 
 *
 */
@ComponentScan(basePackages = {"com.codechasers.chat"})
@SpringBootApplication
@EnableAutoConfiguration
public class ChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatApplication.class, args);
	}

}
