package com.example.marketplace.addproduct.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marketplace.repository.Repository

class AddProduct_ViewModelFactory (private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddProduct_ViewModel(repository) as T
    }
}