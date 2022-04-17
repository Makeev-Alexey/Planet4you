package com.example.myapplication.firstFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.Data.API.ResponseBodyUserInfo
import com.example.myapplication.Data.API.UserInfo
import com.example.myapplication.Data.UserInfoRepo
import kotlinx.coroutines.*
import retrofit2.Response

class UserListViewModel(val userInfoRepo: UserInfoRepo): ViewModel() {
    val userInfoList = MutableLiveData<List<UserInfo>>()
    val errorMessage = MutableLiveData<String>()
    var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()
    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
    fun getUserInfoLiistFromDB(){
        CoroutineScope(Dispatchers.IO+exceptionHandler).launch {
            loading.postValue(true)
            val response: Response<ResponseBodyUserInfo> = userInfoRepo.getUserInfo(2)
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    userInfoList.postValue(userInfoRepo.getAllAUserInfo())
                }
                else{
                    onError(response.message())
                }
            }
        }
    }
    fun getUserInfoList(){
        job = CoroutineScope(Dispatchers.IO+exceptionHandler).launch {
            loading.postValue(true)
            val response: Response<ResponseBodyUserInfo> = userInfoRepo.getUserInfo(2)
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    response.body()?.let { userInfoRepo.addUserInfo(it.data) }
                    userInfoList.postValue(userInfoRepo.getAllAUserInfo())
                }
                else{
                    onError(response.message())
                }
            }
        }
    }
}