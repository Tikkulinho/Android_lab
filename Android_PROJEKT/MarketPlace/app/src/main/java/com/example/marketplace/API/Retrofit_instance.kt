package com.example.marketplace.API

import com.example.marketplace.utils.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit_instance {
    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val api:MarketAPI by lazy {
        retrofit.create(MarketAPI :: class.java)
    }
}