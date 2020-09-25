package br.com.myproject.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
public class MessageWebController {
	
	@GetMapping("/message")
	public String test() {
		return "Hello JavaInUse Called in First Service";
	}

}
