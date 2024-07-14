package com.example.learningApi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LearningApiApplication

fun main(args: Array<String>) {
	runApplication<LearningApiApplication>(*args)
}

// Tomcat started on port 2020 (http) with context path '/'