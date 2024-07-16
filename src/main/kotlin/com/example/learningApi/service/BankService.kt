package com.example.learningApi.service

import com.example.learningApi.datasource.BankDataSource
import com.example.learningApi.model.Bank
import org.springframework.stereotype.Service

@Service
class BankService(
    private val dataSource: BankDataSource
) {
    fun getBanks(): Collection<Bank> = dataSource.retrieveBanks()
    fun getBank(accountNumber: String): Bank = dataSource.retrieveBank(accountNumber)
    fun addBank(bank: Bank): Bank = dataSource.createBank(bank)
    fun updateBank(bank: Bank): Bank = dataSource.updateBank(bank)

}