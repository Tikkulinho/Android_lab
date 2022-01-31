package com.example.marketplace.update.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplace.MyApplication
import com.example.marketplace.User
import com.example.marketplace.repository.Repository
import com.example.marketplace.update.model.UpdateRequest
import kotlinx.coroutines.launch

class Update_ViewModel(val repository: Repository) : ViewModel() {
    var token: MutableLiveData<String> = MutableLiveData()
    var user = MutableLiveData<User>()

    init {
        user.value = User()
    }

    fun updateUser() {
        viewModelScope.launch {
            val request = UpdateRequest(
                username = user.value!!.username,
                email = user.value!!.email,
                phone_number = user.value!!.phone_number
            )
            try {
                val result = repository.updateUser(MyApplication.token, request)
                Log.d("UpdateViewModel ok", "UpdateViewModel - #users:  $result")
            } catch (e: Exception) {

                Log.d("UpdateViewModel fail", "UpdateViewModel exception: ${e.toString()}")
            }
        }
    }
}