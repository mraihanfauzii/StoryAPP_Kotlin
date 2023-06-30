package com.example.submission_awal_intermediate_androidview.stories.upload

import com.google.gson.annotations.SerializedName

data class FileUploadResponse (
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String
)