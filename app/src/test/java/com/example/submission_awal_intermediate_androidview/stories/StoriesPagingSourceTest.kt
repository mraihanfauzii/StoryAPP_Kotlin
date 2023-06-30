package com.example.submission_awal_intermediate_androidview.stories

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.submission_awal_intermediate_androidview.model.Story
import org.junit.Assert.*

class StoriesPagingSourceTest: PagingSource<Int, LiveData<Story>>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LiveData<Story>> {
        return LoadResult.Page(emptyList(),0,1)
    }

    override fun getRefreshKey(state: PagingState<Int, LiveData<Story>>): Int? {
        return 0
    }

    companion object {
        fun snapshot(story: List<Story>): PagingData<Story> {
            return PagingData.from(story)
        }
    }
}