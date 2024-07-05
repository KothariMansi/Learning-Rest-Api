package com.example.learningApi

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/hello")
class HelloWorldController {

    @GetMapping("/rest")
    fun helloWorld(): String = "Hello this is a Rest endpoint."

    @GetMapping("/new")
    fun newWorld(): String = "Hello this is a NewText"
}