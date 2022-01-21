package com.example.marketplace.timeline.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marketplace.repository.Repository

class Timeline_ViewModelFactory (private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return Timeline_ViewModel(repository) as T
    }
}