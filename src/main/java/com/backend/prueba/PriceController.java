package com.backend.prueba;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class PriceController {
    @GetMapping("/")
    public String getHelloWorld() {
        return "Hello, World!";
    }
}
