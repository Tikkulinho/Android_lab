package com.example.marketplace.API

import com.example.marketplace.login.model.LoginRequest
import com.example.marketplace.login.model.LoginResponse
import com.example.marketplace.timeline.model.ProductResponse
import com.example.marketplace.utils.Constants
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface MarketAPI {
    @POST(Constants.LOGIN_URL)
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @GET(Constants.GET_PRODUCT_URL)
    suspend fun getProducts(@Header("token") token: String): ProductResponse
}