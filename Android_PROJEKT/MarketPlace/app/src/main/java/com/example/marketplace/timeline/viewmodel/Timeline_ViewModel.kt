package com.example.marketplace.timeline.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplace.MyApplication
import com.example.marketplace.repository.Repository
import com.example.marketplace.timeline.model.Model_Timeline
import kotlinx.coroutines.launch

class Timeline_ViewModel (private val repository: Repository) : ViewModel() {
    var products: MutableLiveData<List<Model_Timeline>> = MutableLiveData()
    var currentpos: Int = 0
    var remove: MutableLiveData<String> = MutableLiveData()

    init{
        Log.d("xxx", "ListViewModel constructor - Token: ${MyApplication.token}")
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
            try {
                val result = repository.getProducts(MyApplication.token, MyApplication.limit)
                products.value = result.products
                Log.d("xxx", "TimelineViewModel - #products:  ${result.item_count}")
            }catch(e: Exception){
                Log.d("xxx", "TimelineViewModel exception: ${e.toString()}")
            }
        }
    }

    fun getProduct(): Model_Timeline{
        return products.value!![currentpos]
    }

    fun productRemove(){
        viewModelScope.launch{
            try{
                val result = repository.productRemove(MyApplication.token, products.value!![currentpos].product_id)
                remove.value = result.product_id
                Log.d("xxx", "RemoveViewModel - #delete:  ${result.product_id}")
            }catch(e: Exception){
                Log.d("xxx", "RemoveViewModel exception delete: ${e.toString()}")
            }
        }
    }
}