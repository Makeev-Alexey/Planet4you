package com.example.myapplication.Data.API


data class ResponseBodyUserInfo (
    val page: Long,

    val perPage: Long,

    val total: Long,

    val totalPages: Long,

    val data: List<UserInfo>,
    val support: Support
)