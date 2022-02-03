package com.example.marketplace.productupdate.model

data class ProductUpdateRequest(
    var title: String?,
    var price: String?,
    var is_active: Boolean?,
    var units: String?,
    var description:String?
)

data class ProductUpdateInfo(
    var rating: Double,
    var amount_type: String,
    var price_type: String,
    var product_id: String,
    var username: String,
    var is_active: Boolean,
    var price_per_unit: String,
    var units: String,
    var description: String,
    var title: String,
    val creation_time: Long
)

data class ProductUpdateResponse(
    var productUpdateInfo: ProductUpdateInfo
)