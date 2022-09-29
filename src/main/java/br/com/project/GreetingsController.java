package br.com.project;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {
	
	private static final String tamplate = "Hello, %s!";
	private final AtomicLong count = new AtomicLong();
	
	@RequestMapping("/greeting")
	public Greeting greeting (
			@RequestParam(name = "name", defaultValue = "word") String name) {
		return new Greeting (count.incrementAndGet(), String.format(tamplate, name));
		
	}
	
	

}
