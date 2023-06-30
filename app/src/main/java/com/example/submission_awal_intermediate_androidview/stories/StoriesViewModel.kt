package com.example.submission_awal_intermediate_androidview.stories

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.submission_awal_intermediate_androidview.model.Story

class StoriesViewModel(repository: StoriesPagingRepository): ViewModel() {
    val stories: LiveData<PagingData<Story>> = repository.getStories().cachedIn(viewModelScope)
}