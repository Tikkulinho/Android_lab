package com.example.marketplace.profile.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplace.User
import com.example.marketplace.repository.Repository
import kotlinx.coroutines.launch

class Profile_ViewModel(private val repository: Repository) : ViewModel()
{
    val user: MutableLiveData<User> = MutableLiveData()
    init {

        user.value = User()
    }

    fun getUserInfo() {
        viewModelScope.launch {
            try {
                val result = repository.getUserInfo(user.value!!.username)
                user.value = result.data[0]
                Log.d("UserInfoViewModel ok", "UserInfoViewModel - #user:  ${result.code}")
            } catch (e: Exception) {
                Log.d("UserInfoViewModel fail", "UserInfoViewModel exception: ${e.toString()}")
            }
        }
    }
}