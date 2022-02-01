package com.example.marketplace.login.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplace.MyApplication
import com.example.marketplace.User
import com.example.marketplace.login.model.LoginRequest
import com.example.marketplace.repository.Repository
import kotlinx.coroutines.launch

class Login_ViewModel(val repository: Repository) : ViewModel() {
    var token: MutableLiveData<String> = MutableLiveData()
    var user = MutableLiveData<User>()

    init {
        user.value = User()
    }

    fun login() {
        viewModelScope.launch {
            val request = LoginRequest(username = user.value!!.username, password = user.value!!.password)
            try {
                val result = repository.login(request)
                MyApplication.token = result.token
                MyApplication.username = result.username
                token.value = result.token
                Log.d("xxx", "MyApplication - token:  ${MyApplication.token}")
            }catch(e: Exception){
                Log.d("xxx", "MainViewModel - exception: ${e.toString()}")
            }
        }
    }
}