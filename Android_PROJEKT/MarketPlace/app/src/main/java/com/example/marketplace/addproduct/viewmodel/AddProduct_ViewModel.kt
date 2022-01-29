package com.example.marketplace.addproduct.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplace.MyApplication
import com.example.marketplace.repository.Repository
import com.example.marketplace.timeline.model.Model_Timeline
import kotlinx.coroutines.launch

class AddProduct_ViewModel (val repository: Repository) : ViewModel() {
    var product = MutableLiveData<Model_Timeline>()

    init {
        product.value = Model_Timeline()
    }

    fun addProduct() {
        viewModelScope.launch {
            try {
                val result = repository.addProduct(
                    MyApplication.token,
                    title = product.value!!.title,
                    description = product.value!!.description,
                    price_per_unit = product.value!!.price_per_unit,
                    units = product.value!!.units)
                Log.d("xxx", "AddProduct")
            }catch(e: Exception){
                Log.d("xxx", "MainViewModel - exception: ${e.toString()}")
            }
        }
    }

}