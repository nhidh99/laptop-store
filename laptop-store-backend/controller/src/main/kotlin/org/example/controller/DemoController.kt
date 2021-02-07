package org.example.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class DemoController {
    @GetMapping("/demo")
    fun demoGetRequest(): String {
        return "Hello World!"
    }
}