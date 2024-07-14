package com.example.learningApi.datasource

import com.example.learningApi.model.Bank

interface BankDataSource {
    fun retrieveBanks() : Collection<Bank>
    fun retrieveBank(accountNumber: String): Bank
}