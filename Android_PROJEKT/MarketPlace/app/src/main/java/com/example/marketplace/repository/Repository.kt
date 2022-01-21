package com.example.marketplace.repository

import android.util.Log
import com.example.marketplace.API.Retrofit_instance
import com.example.marketplace.login.model.LoginRequest
import com.example.marketplace.login.model.LoginResponse
import com.example.marketplace.timeline.model.ProductResponse

//import com.example.marketplace.model.ProductResponse

class Repository {
    suspend fun login(request: LoginRequest): LoginResponse {
        return Retrofit_instance.api.login(request)
    }

    suspend fun getProducts(token: String): ProductResponse {
        return Retrofit_instance.api.getProducts(token)
    }
}