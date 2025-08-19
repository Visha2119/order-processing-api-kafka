package com.process.order_service.model

data class Order(
    val orderId: Long,
    val customerId: String,
    val product: String,
    val quantity: Int,
    val status: String
)
