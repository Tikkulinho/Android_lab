package com.example.marketplace.forgotpassword.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplace.User
import com.example.marketplace.forgotpassword.model.ForgotPasswordRequest
import com.example.marketplace.repository.Repository
import kotlinx.coroutines.launch

class ForgotPassword_ViewModel (val repository: Repository) : ViewModel() {
    var user = MutableLiveData<User>()

    init {
        user.value = User()
    }

    fun forgotpassword() {
        viewModelScope.launch {
            val request =
                ForgotPasswordRequest(username= user.value!!.username, email = user.value!!.email)
            try {
                val result = repository.forgotpassword(request)
                Log.d("xxx", "Register")
            }catch(e: Exception){
                Log.d("xxx", "MainViewModel - exception: ${e.toString()}")
            }
        }
    }

}