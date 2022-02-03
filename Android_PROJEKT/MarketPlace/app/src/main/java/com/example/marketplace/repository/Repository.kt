package com.example.marketplace.repository

import android.util.Log
import com.example.marketplace.API.Retrofit_instance
import com.example.marketplace.addorder.model.AddOrderRequest
import com.example.marketplace.addorder.model.AddOrderResponse
import com.example.marketplace.addproduct.model.AddProductResponse
import com.example.marketplace.forgotpassword.model.ForgotPasswordRequest
import com.example.marketplace.forgotpassword.model.ForgotPasswordResponse
import com.example.marketplace.login.model.LoginRequest
import com.example.marketplace.login.model.LoginResponse
import com.example.marketplace.myfares.model.MyFareResponse
import com.example.marketplace.productremove.model.RemoveResponse
import com.example.marketplace.productupdate.model.ProductUpdateRequest
import com.example.marketplace.productupdate.model.ProductUpdateResponse
import com.example.marketplace.profile.model.ProfileResponse
import com.example.marketplace.register.model.RegisterRequest
import com.example.marketplace.register.model.RegisterResponse
import com.example.marketplace.timeline.model.ProductResponse
import com.example.marketplace.update.model.UpdateRequest
import com.example.marketplace.update.model.UpdateResponse


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

    suspend fun getUserInfo(username: String): ProfileResponse{
        return Retrofit_instance.api.getUserInfo(username)
    }

    suspend fun updateUser(token: String, request: UpdateRequest): UpdateResponse {
        return Retrofit_instance.api.updateUser(token, request)
    }

    suspend fun productUpdate(token :String, product_id: String, request: ProductUpdateRequest) : ProductUpdateResponse {
        return Retrofit_instance.api.productUpdate(token,product_id, request)
    }

    suspend fun productRemove(token:String, product_id:String): RemoveResponse {
        return Retrofit_instance.api.productRemove(token,product_id)
    }

    suspend fun myFareOrder(token:String): MyFareResponse {
        return Retrofit_instance.api.myFareOrder(token)
    }

    suspend fun addOrder(token:String, request: AddOrderRequest): AddOrderResponse{
        return Retrofit_instance.api.addOrder(token, request)
    }

}