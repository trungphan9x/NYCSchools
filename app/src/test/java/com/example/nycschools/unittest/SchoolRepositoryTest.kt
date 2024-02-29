package com.example.nycschools.unittest

import com.example.nycschools.network.ApiService
import com.example.nycschools.repository.SchoolPagingSource
import com.example.nycschools.repository.SchoolRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock


class SchoolRepositoryTest {
    private lateinit var repository: SchoolRepository
    private val apiService = mock(ApiService::class.java)

    @Before
    fun setUp() {
        repository = SchoolRepository(apiService)
    }

    @Test
    fun `getSchoolPagingSource returns correct PagingSource` () {
        //Execute
        val result = repository.getSchoolsPagingSource()

        //Verify
        assertThat(result).isInstanceOf(SchoolPagingSource::class.java)
    }
}