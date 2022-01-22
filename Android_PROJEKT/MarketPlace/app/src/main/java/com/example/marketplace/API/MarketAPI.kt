package com.example.marketplace.API

import com.example.marketplace.forgotpassword.model.ForgotPasswordRequest
import com.example.marketplace.forgotpassword.model.ForgotPasswordResponse
import com.example.marketplace.login.model.LoginRequest
import com.example.marketplace.login.model.LoginResponse
import com.example.marketplace.register.model.RegisterRequest
import com.example.marketplace.register.model.RegisterResponse
import com.example.marketplace.timeline.model.ProductResponse
import com.example.marketplace.utils.Constants
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface MarketAPI {
    @POST(Constants.LOGIN_URL)
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST(Constants.REGISTER_URL)
    suspend fun register(@Body request: RegisterRequest): RegisterResponse

    @POST(Constants.FORGOTPASSWORD_URL)
    suspend fun forgotpassword(@Body request: ForgotPasswordRequest): ForgotPasswordResponse

    @GET(Constants.GET_PRODUCT_URL)
    suspend fun getProducts(@Header("token") token: String): ProductResponse
}