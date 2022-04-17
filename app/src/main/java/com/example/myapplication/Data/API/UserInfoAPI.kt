package com.example.myapplication.Data.API

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserInfoAPI {
    @GET("/api/users")
    suspend fun getUserInfo(@Query("page") page: Int): Response<ResponseBodyUserInfo>
}