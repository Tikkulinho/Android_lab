package com.example.marketplace.repository

import android.util.Log
import com.example.marketplace.API.Retrofit_instance
import com.example.marketplace.addproduct.model.AddProductResponse
import com.example.marketplace.forgotpassword.model.ForgotPasswordRequest
import com.example.marketplace.forgotpassword.model.ForgotPasswordResponse
import com.example.marketplace.login.model.LoginRequest
import com.example.marketplace.login.model.LoginResponse
import com.example.marketplace.register.model.RegisterRequest
import com.example.marketplace.register.model.RegisterResponse
import com.example.marketplace.timeline.model.ProductResponse

//import com.example.marketplace.model.ProductResponse

class Repository {
    suspend fun login(request: LoginRequest): LoginResponse {
        return Retrofit_instance.api.login(request)
    }

    suspend fun register(request: RegisterRequest): RegisterResponse {
        return Retrofit_instance.api.register(request)
    }

    suspend fun forgotpassword(request: ForgotPasswordRequest): ForgotPasswordResponse {
        return Retrofit_instance.api.forgotpassword(request)
    }

    suspend fun getProducts(token: String, limit: Int): ProductResponse {
        return Retrofit_instance.api.getProducts(token, limit)
    }

    suspend fun addProduct(
        token: String, title: String?, description: String?, price_per_unit: String?,
        units: String?
    ): AddProductResponse {
        return Retrofit_instance.api.addProduct(token, title, description, price_per_unit, units)
    }
}