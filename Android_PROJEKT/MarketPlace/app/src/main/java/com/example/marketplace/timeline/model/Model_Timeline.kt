package com.example.marketplace.timeline.model

data class Model_Timeline(var rating: Double = 0.0,
                   var amount_type: String = "",
                   var price_type: String = "",
                   var product_id: String = "",
                   var username: String = "",
                   var is_active: Boolean = false,
                   var price_per_unit: String = "",
                   var units: String = "",
                   var description: String = "",
                   var title: String = "",
                   var creation_time: Long = 0
)

data class ProductResponse(val item_count: Int, val products: List<Model_Timeline>, val timestamp: Long)