package com.example.learningApi.controller

import com.example.learningApi.model.Bank
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.patch
import org.springframework.test.web.servlet.post
import kotlin.test.Test

@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest @Autowired constructor(
     val mockMvc: MockMvc,
     var objectMapper: ObjectMapper
) {
    val baseUrl = "/api/banks"

    @Nested
    @DisplayName("GET/ api/ bank")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetBanks {
        @Test
        fun `should return all banks` () {
            //WHEN/THEN
            mockMvc.get(baseUrl)
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].accountNumber"){ value("123456") }
                }
        }
    }

    @Nested
    @DisplayName("GET/ api/ banks/ {accountNumber}")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetBank{
        @Test
        fun `should return the bank with the given account number` () {
            //GIVEN$
            val accountNumber = 1234

            //WHEN$//THEN$
            mockMvc.get("$baseUrl/$accountNumber")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.trust") {value(100.0)}
                    jsonPath("$.transactionFee") {value(100)}
                }
        }

        @Test
        fun `should should return NOT FOUND if the account Number doesn't exist` () {
            //GIVEN
            val accountNumber = "does_not_exist"

            //WHEN//THEN
            mockMvc.get("$baseUrl/$accountNumber")
                .andDo { print() }
                .andExpect { status { isNotFound() } }

        }

    }

    @Nested
    @DisplayName("POST/ api/ banks")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class PostNewBank{
        @Test
        fun `should add the new bank` () {
            //GIVEN
            val newBank = Bank(accountNumber = "acc123", trust = 67.24, transactionFee = 56)
            
            //WHEN
            val performPost = mockMvc.post(baseUrl){
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newBank)
            }
            //THEN
            performPost
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(newBank))
                    }
                }
            mockMvc.get("$baseUrl/${newBank.accountNumber}")
                .andExpect { content { json(objectMapper.writeValueAsString(newBank)) } }

        }
        
        @Test
        fun `should return BAD REQUEST if bank with given account number already exists` () {
            //GIVEN
            val invalidateBank = Bank("1234", 1.0, 4)
            
            //WHEN
            val performPost = mockMvc.post(baseUrl){
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidateBank)
            }
            
            //THEN
            performPost
                .andDo { print() }
                .andExpect { status { isBadRequest() } }
        }
    }
    @Nested
    @DisplayName("PATCH/ api/ banks")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class PatchExistingBank {
        @Test
        fun `should update an existing bank` () {
            //GIVEN
            val updateBank = Bank("1234", 1.0, 47)
            //WHEN
            val performPatch = mockMvc.patch(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updateBank)
            }
            //THEN
            performPatch
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(updateBank))
                    }
                }
            mockMvc.get("$baseUrl/${updateBank.accountNumber}")
                .andExpect { content { json(objectMapper.writeValueAsString(updateBank)) } }

        }
        @Test
        fun `should return BAD REQUEST if no bank with given account number exists` () {
            val invalidateBank = Bank("does_not_exists", 1.0, 4)

            //WHEN
            val performPatch = mockMvc.patch(baseUrl){
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidateBank)
            }

            //THEN
            performPatch
                .andDo { print() }
                .andExpect { status { isNotFound() } }

        }
    }
}