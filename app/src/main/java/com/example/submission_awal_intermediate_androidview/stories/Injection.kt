package com.example.submission_awal_intermediate_androidview.stories

import com.example.submission_awal_intermediate_androidview.api.ApiConfig

object Injection {
    fun provideRepository(token:String) : StoriesPagingRepository{

        val apiService = ApiConfig.getApiService()

        return StoriesPagingRepository(apiService, token)
    }
}