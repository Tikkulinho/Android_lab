package com.example.marketplace.myfares.model

data class MyFareInfo(
    var order_id: String,
    var username: String,
    var status: String,
    var owner_username: String,
    var price_per_unit: String,
    var units: String,
    var description: String,
    var title: String,
    var creation_time: Long,
)

data class MyFareResponse(
    var item_count: Int,
    var myFareInfo: List<MyFareInfo>
)