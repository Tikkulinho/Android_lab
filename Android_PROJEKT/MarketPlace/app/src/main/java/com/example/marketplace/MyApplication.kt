package com.example.marketplace

import android.app.Application

class MyApplication: Application(){
    companion object{
        var token: String =""
        var username: String = ""
        var limit: Int = 0
    }
}