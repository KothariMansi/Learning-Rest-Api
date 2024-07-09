package com.example.learningApi.controller

import com.example.learningApi.model.Bank
import com.example.learningApi.service.BankService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class BankController(
    private val service: BankService
) {
    @GetMapping("api/banks")
    fun getBanks(): Collection<Bank> = service.getBanks()

}