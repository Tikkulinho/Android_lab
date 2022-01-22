package com.example.marketplace.forgotpassword.model

import com.google.gson.annotations.SerializedName

//GSon converter
data class ForgotPasswordRequest (
    @SerializedName("username")
    var username: String,

    @SerializedName("email")
    var email: String,

)


data class ForgotPasswordResponse (
    @SerializedName("code")
    var code: Int,

    @SerializedName("message")
    var message: String,

    @SerializedName("timestamp")
    var timestamp: Long
)