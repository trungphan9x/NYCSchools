package com.example.nycschools.repository

import com.example.nycschools.network.ApiService
import javax.inject.Inject

class SchoolRepository @Inject constructor (private val apiService: ApiService){
    fun getSchoolsPagingSource() = SchoolPagingSource(apiService)
}