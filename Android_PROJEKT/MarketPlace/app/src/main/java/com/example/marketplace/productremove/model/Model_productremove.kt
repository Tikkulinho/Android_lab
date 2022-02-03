package com.example.marketplace.productremove.model

data class RemoveResponse(
    var message: String,
    var product_id: String,
    var delete_time: Long
)