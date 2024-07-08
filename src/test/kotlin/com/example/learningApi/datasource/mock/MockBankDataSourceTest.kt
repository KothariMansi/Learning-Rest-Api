package com.example.learningApi.datasource.mock

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MockBankDataSourceTest{
    private val mockBankDataSource = MockBankDataSource()
    @Test
    fun `should provide a list or a collection of banks` () {
        //WHEN$
        val banks = mockBankDataSource.retrieveBanks()

        //THEN$
        assertThat(banks.size).isGreaterThanOrEqualTo(3)
    }

    @Test
    fun `should provide mock data` () {
        //WHEN$
        val banks = mockBankDataSource.retrieveBanks()

        //THEN$
        assertThat(banks).allMatch{it.accountNumber.isNotBlank()}
        assertThat(banks).anyMatch{it.trust != 0.0}
        assertThat(banks).anyMatch{it.transactionFee != 0}

    }






}