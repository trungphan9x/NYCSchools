package com.example.nycschools.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.nycschools.model.School
import com.example.nycschools.network.ApiService

class SchoolPagingSource(private val apiService: ApiService) : PagingSource<Int, School>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, School> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val offset = position * PAGE_SIZE
            val limit = params.loadSize
            val response = apiService.getNYCSchools(offset = offset, limit = limit)

            if (response.isSuccessful) {
                val schools = response.body() ?: emptyList()
                val nextKey =
                    if (schools.isEmpty()) null else position + (params.loadSize / PAGE_SIZE)
                LoadResult.Page(
                    data = schools,
                    prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                    nextKey = nextKey
                )
            } else {
                LoadResult.Error(Exception("API Error with Response Code: ${response.code()}"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, School>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.nextKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 0
        private const val PAGE_SIZE = 20
    }
}