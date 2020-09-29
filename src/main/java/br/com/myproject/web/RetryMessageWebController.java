package br.com.myproject.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/retry")
public class RetryMessageWebController {
	
	private int counter;
	
	@GetMapping("/")
	public String retry() {
		counter++;
		if(counter % 2 == 0) throw new RuntimeException("retry doesnt work");
		return  "retry works";
	}

}
