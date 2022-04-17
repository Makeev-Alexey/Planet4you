package com.example.myapplication.Data.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUserInfo {

    val baseUrl = "https://reqres.in"

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}