package com.example.marketplace.myfares.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplace.MyApplication
import com.example.marketplace.myfares.model.MyFareInfo
import com.example.marketplace.repository.Repository
import kotlinx.coroutines.launch

class MyFare_ViewModel(private val repository: Repository) : ViewModel() {
    var products: MutableLiveData<List<MyFareInfo>> = MutableLiveData()

    init{
        Log.d("xxx", "MyFareViewModel constructor - Token: ${MyApplication.token}")
        myFareOrder()
    }

    fun myFareOrder() {
        viewModelScope.launch {
            try {
                val result = repository.myFareOrder(MyApplication.token)
                products.value = result.myFareInfo
                Log.d("xxx", "MyFareViewModel - #products:  ${result.item_count}")
            }catch(e: Exception){
                Log.d("xxx", "MyFareViewModel exception: ${e.toString()}")
            }
        }
    }
}