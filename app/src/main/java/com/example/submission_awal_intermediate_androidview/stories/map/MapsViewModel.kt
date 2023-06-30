package com.example.submission_awal_intermediate_androidview.stories.map

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.submission_awal_intermediate_androidview.R
import com.example.submission_awal_intermediate_androidview.api.ApiConfig
import com.example.submission_awal_intermediate_androidview.model.Story
import com.example.submission_awal_intermediate_androidview.stories.upload.GetStoriesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapsViewModel(application: Application): AndroidViewModel(application) {

    val stories = MutableLiveData<ArrayList<Story>>()

    fun getStoryLocation(token: String) {
        ApiConfig.getApiService().getStoryLocation("Bearer $token", 20, 1).enqueue(object:
            Callback<GetStoriesResponse> {
            override fun onResponse(
                call: Call<GetStoriesResponse>,
                response: Response<GetStoriesResponse>
            ) {
                if (response.isSuccessful) {
                    stories.postValue(response.body()?.listStory)
                }
            }

            override fun onFailure(call: Call<GetStoriesResponse>, t: Throwable) {
                Toast.makeText(getApplication(), getApplication<Application>().getString(R.string.load_maps_failed), Toast.LENGTH_SHORT).show()
                Log.d("Failure: ", "${t.message}")
            }
        })
    }

    fun getSomeLocation(): LiveData<ArrayList<Story>> {
        return stories
    }
}