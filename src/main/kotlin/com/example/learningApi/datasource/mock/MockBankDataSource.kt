package com.example.learningApi.datasource.mock

import com.example.learningApi.datasource.BankDataSource
import com.example.learningApi.model.Bank
import org.springframework.stereotype.Repository

@Repository
class MockBankDataSource: BankDataSource {
    val banks = listOf(
        Bank("123456", 12.6, 56),
        Bank("1010", 45.5, 0),
        Bank("1234", 100.0, 100)
    )

    override fun retrieveBanks(): Collection<Bank> = banks
}