package com.example.nycschools.network

import com.example.nycschools.model.School
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET ("resource/s3k6-pzi2.json")
    suspend fun  getNYCSchools(@Query("\$offset") offset: Int, @Query("\$limit") limit: Int) : Response<List<School>>
}