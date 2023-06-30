package com.example.submission_awal_intermediate_androidview.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("userId")
    val userId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("token")
    val token: String
)
