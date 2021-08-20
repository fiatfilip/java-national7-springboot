package ro.siit.demoSpringBoot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    @GetMapping("/test")
    public String index(@RequestParam(name="nume", defaultValue = "John Doe") String name) {
        return "{\"user\": \""+ name +"\"}";
    }

}
