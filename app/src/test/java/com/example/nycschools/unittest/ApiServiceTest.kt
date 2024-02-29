package com.example.nycschools.unittest

import com.example.nycschools.model.School
import com.example.nycschools.network.ApiService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class ApiServiceTest {
    @Mock
    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        //Initialize Mock
    }

    @Test
    fun `fest school list successfully`() = runBlocking {
        // Mock API response
        val mockResponse = listOf(School("dbn", "name", "overview"))

        whenever(apiService.getNYCSchools(anyInt(), anyInt())).thenReturn(
            Response.success(mockResponse)
        )

        // Call the API
        val response = apiService.getNYCSchools(0,10)

        // Verify the response
        assertTrue(response.isSuccessful)
        assertEquals(mockResponse, response.body())
    }
}