package com.example.myapplication.Data

import android.content.Context
import androidx.room.Delete
import androidx.room.Update
import com.example.myapplication.Data.API.ResponseBodyUserInfo
import com.example.myapplication.Data.API.RetrofitUserInfo
import com.example.myapplication.Data.API.UserInfo
import com.example.myapplication.Data.API.UserInfoAPI
import com.example.myapplication.Data.DB.UserInfoDao
import com.example.myapplication.Data.DB.UserInfoDatabaseBuilder
import retrofit2.Response
import retrofit2.http.Query

class UserInfoRepo(context: Context) {
    var userInfoDao = UserInfoDatabaseBuilder.getUserInstance(context)?.getUserInfoDao()
    suspend fun getUserInfo(page: Int): Response<ResponseBodyUserInfo>{
        return RetrofitUserInfo.getInstance().create(UserInfoAPI::class.java).getUserInfo(page)
    }
    suspend fun addUserInfo(userInfoList: List<UserInfo>) {
        userInfoDao?.addUserInfo(userInfoList)
    }
    suspend fun getAllAUserInfo(): List<UserInfo> {
        return userInfoDao?.getAllAUserInfo() ?: mutableListOf()
    }
    suspend fun deleteAllUserInfo() {
        userInfoDao?.deleteAllUserInfo()
    }
    suspend fun updateUserInfo(userInfo: UserInfo) {
        userInfoDao?.updateUserInfo(userInfo)
    }
    suspend fun deleteUserInfo(userInfo: UserInfo) {
        userInfoDao?.deleteUserInfo(userInfo)
    }
}