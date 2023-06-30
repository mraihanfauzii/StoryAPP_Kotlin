package com.example.submission_awal_intermediate_androidview.stories

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.submission_awal_intermediate_androidview.api.ApiService
import com.example.submission_awal_intermediate_androidview.model.Story

class StoriesPagingRepository(private val apiService: ApiService, private val token: String)  {
    fun getStories(): LiveData<PagingData<Story>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                StoriesPagingSource(apiService, token)
            }
        ).liveData
    }
}