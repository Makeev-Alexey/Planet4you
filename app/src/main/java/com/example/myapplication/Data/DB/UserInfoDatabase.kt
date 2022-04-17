package com.example.myapplication.Data.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.Data.API.UserInfo
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [UserInfo::class], version = 1)
abstract class UserInfoDatabase: RoomDatabase() {
    abstract fun getUserInfoDao(): UserInfoDao
}
object UserInfoDatabaseBuilder{
    var instance: UserInfoDatabase? = null
    @OptIn(InternalCoroutinesApi::class)
    fun getUserInstance(context: Context): UserInfoDatabase? {
        if (instance == null) {
            synchronized(UserInfoDatabase::class.java){
                instance = Room.databaseBuilder(context.applicationContext, UserInfoDatabase::class.java, "MyDatabase").build()
            }
        }
        return instance
    }
}