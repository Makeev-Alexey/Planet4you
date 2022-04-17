package com.example.myapplication.Data.DB

import androidx.room.*
import com.example.myapplication.Data.API.UserInfo

@Dao
interface UserInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUserInfo(userInfoList: List<UserInfo>)
    @Query("SELECT * FROM userInfo")
    suspend fun getAllAUserInfo(): List<UserInfo>
    @Query("DELETE FROM userInfo")
    suspend fun deleteAllUserInfo()
    @Update
    suspend fun updateUserInfo(userInfo: UserInfo)
    @Delete
    suspend fun deleteUserInfo(userInfo: UserInfo)
}