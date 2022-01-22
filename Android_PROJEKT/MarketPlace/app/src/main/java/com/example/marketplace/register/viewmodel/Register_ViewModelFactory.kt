package com.example.marketplace.register.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marketplace.login.viewmodel.Login_ViewModel
import com.example.marketplace.repository.Repository

class Register_ViewModelFactory (private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return Register_ViewModel(repository) as T
    }
}