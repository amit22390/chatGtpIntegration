package com.example.chatgtpintegration.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.chatgtpintegration.model.ChatCompletionMessage;
import com.example.chatgtpintegration.model.ChatCompletionMessage.Role;
import com.example.chatgtpintegration.service.OpenAIService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/open/ai")
public class OpenAIController {

	@Autowired
	OpenAIService service;

	@PostMapping("/char")
	public Flux<String> chat(@RequestBody String prompt) {
		List<ChatCompletionMessage> list = new ArrayList<>();
		list.add(new ChatCompletionMessage(Role.ASSISTANT, prompt));
		return service.generateCompletionStream(list);
	}
}
