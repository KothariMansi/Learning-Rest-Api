package com.example.learningApi.datasource

import com.example.learningApi.model.Bank

interface BankDataSource {
    fun retrieveBanks() : Collection<Bank>
    fun retrieveBank(accountNumber: String): Bank
    fun createBank(bank: Bank): Bank
    fun updateBank(bank: Bank): Bank
}