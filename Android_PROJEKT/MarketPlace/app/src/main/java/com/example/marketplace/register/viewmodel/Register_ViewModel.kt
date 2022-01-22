package com.example.marketplace.register.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplace.MyApplication
import com.example.marketplace.User
import com.example.marketplace.register.model.RegisterRequest
import com.example.marketplace.repository.Repository
import kotlinx.coroutines.launch

class Register_ViewModel(val repository: Repository) : ViewModel() {
    var user = MutableLiveData<User>()

    init {
        user.value = User()
    }

    fun register() {
        viewModelScope.launch {
            val request =
                RegisterRequest(username = user.value!!.username, password = user.value!!.password, email = user.value!!.email, phone_number = user.value!!.phone_number)
            try {
                val result = repository.register(request)
                Log.d("xxx", "Register")
            }catch(e: Exception){
                Log.d("xxx", "MainViewModel - exception: ${e.toString()}")
            }
        }
    }

}