package com.example.learningApi.service

import com.example.learningApi.datasource.BankDataSource
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test

internal class BankServiceTest{
    private val dataSource: BankDataSource = mockk(relaxed = true)
    private val bankService = BankService(dataSource)
    @Test
    fun `should call its data source to retrieve data` () {
        //GIVEN$
        // every { dataSource.retrieveBanks() } returns emptyList()

        //WHEN$
        bankService.getBanks()

        //THEN$
        verify(exactly = 1) {dataSource.retrieveBanks()}
    }

}