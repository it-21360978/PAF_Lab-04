package com.example.greeting.greeting;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class greetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong count = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(){
        return new Greeting(count.incrementAndGet(), String.format(template, "World"));
    }
    
    @GetMapping("/greeting/name")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "gihan") String name){
        return new Greeting(count.incrementAndGet(), String.format(template, name));
    }

}
