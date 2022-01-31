package com.example.marketplace.profile.model

import com.example.marketplace.User

data class ProfileResponse(var code: Int, var data: List<User>, val timestamp: Long)