package com.example.submission_awal_intermediate_androidview.api

import com.example.submission_awal_intermediate_androidview.authentication.LoginResponse
import com.example.submission_awal_intermediate_androidview.authentication.RegisterResponse
import com.example.submission_awal_intermediate_androidview.stories.upload.GetStoriesResponse
import com.example.submission_awal_intermediate_androidview.stories.upload.FileUploadResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    fun authenticationRegister(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<RegisterResponse>

    @FormUrlEncoded
    @POST("login")
    fun authenticationLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @GET("stories")
    suspend fun getStories(
        @Header("Authorization") token: String,
        @Query("size") loadSize: Int,
        @Query("page") page: Int
    ): GetStoriesResponse

    @GET("stories")
    fun getStoryLocation(
        @Header("Authorization") token: String,
        @Query("size") loadSize: Int,
        @Query("location") location: Int
    ): Call<GetStoriesResponse>

    @Multipart
    @POST("stories")
    fun uploadStory(
        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part,
        @Part("description") description: RequestBody,
    ): Call<FileUploadResponse>
}