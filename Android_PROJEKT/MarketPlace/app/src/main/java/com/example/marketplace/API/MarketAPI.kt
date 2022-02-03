package com.example.marketplace.API

import com.example.marketplace.addproduct.model.AddProductResponse
import com.example.marketplace.forgotpassword.model.ForgotPasswordRequest
import com.example.marketplace.forgotpassword.model.ForgotPasswordResponse
import com.example.marketplace.login.model.LoginRequest
import com.example.marketplace.login.model.LoginResponse
import com.example.marketplace.productupdate.model.ProductUpdateRequest
import com.example.marketplace.productupdate.model.ProductUpdateResponse
import com.example.marketplace.profile.model.ProfileResponse
import com.example.marketplace.register.model.RegisterRequest
import com.example.marketplace.register.model.RegisterResponse
import com.example.marketplace.timeline.model.ProductResponse
import com.example.marketplace.update.model.UpdateRequest
import com.example.marketplace.update.model.UpdateResponse
import com.example.marketplace.utils.Constants
import com.example.marketplace.utils.Constants.ADD_PRODUCT_URL
import com.example.marketplace.utils.Constants.PRODUCT_UPDATE_URL
import com.example.marketplace.utils.Constants.USER_INFO_URL
import com.example.marketplace.utils.Constants.USER_UPDATE_URL
import retrofit2.http.*

interface MarketAPI {
    @POST(Constants.LOGIN_URL)
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST(Constants.REGISTER_URL)
    suspend fun register(@Body request: RegisterRequest): RegisterResponse

    @POST(Constants.FORGOTPASSWORD_URL)
    suspend fun forgotpassword(@Body request: ForgotPasswordRequest): ForgotPasswordResponse

    @GET(Constants.GET_PRODUCT_URL)
    suspend fun getProducts(@Header("token") token: String, @Header("limit") limit: Int = 1000): ProductResponse

    @Multipart
    @POST(ADD_PRODUCT_URL)
    suspend fun addProduct(
        @Header("token") token: String, @Part("title") title: String?,
        @Part("description") description: String?, @Part("price_per_unit") price_per_unit: String?,
        @Part("units") units: String?
    ): AddProductResponse

    @GET(USER_INFO_URL)
    suspend fun getUserInfo(@Header("username") username: String): ProfileResponse

    @POST(USER_UPDATE_URL)
    suspend fun updateUser(
        @Header("token") token: String,
        @Body request: UpdateRequest
    ): UpdateResponse

    @POST(PRODUCT_UPDATE_URL)
    suspend fun productUpdate(
        @Header("token") token: String,
        @Query("product_id") product_id: String,
        @Body request: ProductUpdateRequest
    ): ProductUpdateResponse
}