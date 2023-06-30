package com.example.submission_awal_intermediate_androidview.authentication

import com.example.submission_awal_intermediate_androidview.model.User
import com.google.gson.annotations.SerializedName

class LoginResponse (
    @SerializedName("loginResult")
    val loginResult: User,
)