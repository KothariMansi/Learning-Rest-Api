package com.example.learningApi.datasource.mock

import com.example.learningApi.datasource.BankDataSource
import com.example.learningApi.model.Bank
import org.springframework.stereotype.Repository

@Repository
class MockBankDataSource: BankDataSource {
    val banks = mutableListOf(
        Bank("123456", 12.6, 56),
        Bank("1010", 45.5, 0),
        Bank("1234", 100.0, 100)
    )

    override fun retrieveBanks(): Collection<Bank> = banks
    override fun retrieveBank(accountNumber: String): Bank =
        banks.firstOrNull {it.accountNumber == accountNumber} ?: throw NoSuchElementException("Could Not found bank with account number $accountNumber")

    override fun createBank(bank: Bank): Bank {
        if (banks.any{it.accountNumber == bank.accountNumber}){
            throw IllegalArgumentException("Bank with account number ${bank.accountNumber} already exist.")
        }
        banks.add(bank)
        return bank
    }

    override fun updateBank(bank: Bank): Bank {
        val currentBank = banks.firstOrNull{it.accountNumber == bank.accountNumber} ?: throw NoSuchElementException("Could Not found bank with account number ${bank.accountNumber}")
        banks.remove(currentBank)
        banks.add(bank)
        return bank
    }

}