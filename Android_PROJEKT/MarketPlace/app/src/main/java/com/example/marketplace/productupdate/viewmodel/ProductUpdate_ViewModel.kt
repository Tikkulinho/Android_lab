package com.example.marketplace.productupdate.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplace.MyApplication
import com.example.marketplace.productupdate.model.ProductUpdateRequest
import com.example.marketplace.repository.Repository
import com.example.marketplace.timeline.model.Model_Timeline
import kotlinx.coroutines.launch

class ProductUpdate_ViewModel(val repository: Repository) : ViewModel(){
    var product = MutableLiveData<Model_Timeline>()
    init {
        product.value = Model_Timeline()
    }

    fun productUpdate() {
        viewModelScope.launch {
            val request = ProductUpdateRequest(
                title = product.value!!.title,
                price = product.value!!.price_per_unit,
                is_active = product.value!!.is_active,
                units = product.value!!.units,
                description = product.value!!.description
            )
            try {
                Log.d("xxx","ID: ${product.value!!.product_id}")
                Log.d("xxx","request: ${request}")
                val result = repository.productUpdate(MyApplication.token, product.value!!.product_id, request)
                Log.d("xxx", "result ${result}")
                Log.d("ProductUpdateViewModel ok", "ProductUpdateViewModel - #product:  $result")
            } catch (e: Exception) {

                Log.d("xxx","ID: ${product.value!!.product_id}")
                Log.d("ProductUpdateViewModel fail", "ProductUpdateViewModel exception: ${e.toString()}")
            }
        }
    }
}