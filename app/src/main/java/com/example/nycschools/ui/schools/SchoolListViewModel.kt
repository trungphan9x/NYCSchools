package com.example.nycschools.ui.schools

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.nycschools.model.School
import com.example.nycschools.repository.SchoolRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SchoolListViewModel @Inject constructor(private val repository: SchoolRepository) : ViewModel(){
    val schools : Flow<PagingData<School>> = Pager(
        config = PagingConfig(enablePlaceholders = false, pageSize = 20 ),
        pagingSourceFactory = {repository.getSchoolsPagingSource()}
    ).flow.cachedIn(viewModelScope)
}