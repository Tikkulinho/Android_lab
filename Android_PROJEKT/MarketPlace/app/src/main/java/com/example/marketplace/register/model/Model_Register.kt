package com.example.marketplace.register.model

import com.google.gson.annotations.SerializedName

//GSon converter
data class RegisterRequest (
    @SerializedName("username")
    var username: String,

    @SerializedName("password")
    var password: String,

    @SerializedName("email")
    var email: String,

    @SerializedName("phone_number")
    var phone_number: String
)


data class RegisterResponse (
    @SerializedName("code")
    var code: Int,

    @SerializedName("message")
    var message: String,

    @SerializedName("timestamp")
    var timestamp: Long
)