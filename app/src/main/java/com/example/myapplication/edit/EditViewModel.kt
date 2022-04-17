package com.example.myapplication.edit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Data.API.UserInfo
import com.example.myapplication.Data.UserInfoRepo
import kotlinx.coroutines.launch

class EditViewModel(var repository: UserInfoRepo) : ViewModel() {
    val muserInfo = MutableLiveData<UserInfo>()
    fun updateUserInfo(userInfo: UserInfo){
        viewModelScope.launch{
            repository.updateUserInfo(userInfo)
    }
    }
    fun deleteUserInfo(userInfo: UserInfo){
        viewModelScope.launch{
            repository.deleteUserInfo(userInfo)
        }
    }

}