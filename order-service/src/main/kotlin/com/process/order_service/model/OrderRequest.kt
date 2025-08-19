package com.process.order_service.model

data class OrderRequest(
    val customerId: String,
    val product: String,
    val quantity: Int
)

