package com.example.myapplication.firstFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.Data.UserInfoRepo
import com.example.myapplication.edit.EditViewModel

class MyViewModelFactory constructor(private val repository: UserInfoRepo): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(UserListViewModel::class.java)) {
            UserListViewModel(this.repository) as T
        } else if (modelClass.isAssignableFrom(EditViewModel::class.java)){
            EditViewModel(repository) as T
        }
        else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}