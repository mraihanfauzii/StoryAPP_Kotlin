package com.example.submission_awal_intermediate_androidview.authentication

import com.google.gson.annotations.SerializedName

class RegisterResponse (
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String
)