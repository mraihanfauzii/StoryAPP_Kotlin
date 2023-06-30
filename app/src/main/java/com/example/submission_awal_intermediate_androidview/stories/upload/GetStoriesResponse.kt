package com.example.submission_awal_intermediate_androidview.stories.upload

import com.example.submission_awal_intermediate_androidview.model.Story
import com.google.gson.annotations.SerializedName

class GetStoriesResponse (
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("listStory")
    val listStory: ArrayList<Story>
)