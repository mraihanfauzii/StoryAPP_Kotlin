package com.example.submission_awal_intermediate_androidview.stories

import com.example.submission_awal_intermediate_androidview.model.Story
import com.example.submission_awal_intermediate_androidview.stories.upload.GetStoriesResponse

object Dummy {

    fun dataDummy(data: Int): GetStoriesResponse {

        val stories = ArrayList<Story>()
        if (data > 0) {
            for (i in 1..data) {
                val detail = Story(
                    id = "$i",
                    name = "$i",
                    description = "$i",
                    photoUrl = "https://story-api.dicoding.dev/images/stories/photos-1684717505761_ZLbiY-7Q.jpg",
                    createdAt = "2023-05-22T01:05:05.763Z",
                    lat = -6.8957643,
                    lon = 107.6338462,
                )
                stories.add(detail)
            }
        }
        return GetStoriesResponse(error = false, message = "Stories fetched successfully", listStory = stories)
    }
}