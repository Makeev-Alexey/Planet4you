package com.example.myapplication.Data.API

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class UserInfo(
    @PrimaryKey(autoGenerate = false)
    val id: Long?,
    val email: String?,
    val firstName: String?,
    val lastName: String?,
    val avatar: String?
) : Parcelable