package com.example.submission_awal_intermediate_androidview.stories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.recyclerview.widget.ListUpdateCallback
import com.example.submission_awal_intermediate_androidview.model.Story
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class StoriesViewModelTest {

    @Mock private lateinit var storiesPagingRepository: StoriesPagingRepository
    private val nullData = ArrayList<Story>()
    private val dummy = Dummy.dataDummy(15)
    @get:Rule
    val executor =  InstantTaskExecutorRule()
    @get:Rule
    val dispatcher = MainDispatcher()

    @Test
    fun success() = runTest {

        val story: PagingData<Story> = StoriesPagingSourceTest.snapshot(dummy.listStory)
        val expectedStory = MutableLiveData<PagingData<Story>>()

        expectedStory.value = story
        Mockito.`when`(storiesPagingRepository.getStories()).thenReturn(expectedStory)

        val storiesViewModel = StoriesViewModel(storiesPagingRepository)
        val data: PagingData<Story> = storiesViewModel.stories.getOrAwaitValue()
        val differ = AsyncPagingDataDiffer(
            diffCallback = StoriesAdapter.DIFF_CALLBACK,
            updateCallback = callbackUpdate,
            workerDispatcher = Dispatchers.Main
        )

        differ.submitData(data)
        assertNotNull(differ.snapshot())
        assertEquals(dummy.listStory, differ.snapshot())
        assertEquals(dummy.listStory.size, differ.snapshot().size)
        assertEquals(dummy.listStory[0], differ.snapshot()[0])
    }

    @Test
    fun loadFail() = runTest {
        val story: PagingData<Story> = StoriesPagingSourceTest.snapshot(nullData)
        val expectedStory = MutableLiveData<PagingData<Story>>()

        expectedStory.value = story
        Mockito.`when`(storiesPagingRepository.getStories()).thenReturn(expectedStory)

        val storiesViewModel = StoriesViewModel(storiesPagingRepository)
        val data: PagingData<Story> = storiesViewModel.stories.getOrAwaitValue()
        val differ = AsyncPagingDataDiffer(
            diffCallback = StoriesAdapter.DIFF_CALLBACK,
            updateCallback = callbackUpdate,
            workerDispatcher = Dispatchers.Main
        )

        differ.submitData(data)
        assertNotNull(differ.snapshot())
        assertEquals(0, differ.snapshot().size)
    }
}

val callbackUpdate = object: ListUpdateCallback {
    override fun onInserted(position: Int, count: Int) {
    }

    override fun onRemoved(position: Int, count: Int) {
    }

    override fun onMoved(fromPosition: Int, toPosition: Int) {
    }

    override fun onChanged(position: Int, count: Int, payload: Any?) {
    }
}