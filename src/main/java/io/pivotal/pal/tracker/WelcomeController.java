package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    String val;

    public WelcomeController(@Value("${welcome.message}") String value){
        this.val=value;
    }
    @GetMapping("/")
    public String sayHello() {
        return val;
    }
}
