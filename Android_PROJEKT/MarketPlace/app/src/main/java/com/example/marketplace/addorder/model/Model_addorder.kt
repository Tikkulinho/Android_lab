package com.example.marketplace.addorder.model

data class AddOrderRequest(
    var title: String = "",
    var description: String = "",
    var price_per_unit: String = "",
    var units: String = "",
    var is_active: Boolean = false,
    var owner_username: String = ""
)

data class AddOrderResponse(
    var creation: String,
    var order_id: String,
    var username: String,
    var is_active: Boolean,
    var price_per_unit: String,
    var units: String,
    var description: String,
    var title: String,
    var creation_time: Long
)