package com.process.order_service.kafka

import com.process.order_service.model.OrderRequest
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class OrderProducer(private val kafkaTemplate: KafkaTemplate<String, OrderRequest>) {
    fun sendOrder(orderRequest: OrderRequest) {
        kafkaTemplate.send("order-placed", orderRequest.customerId, orderRequest)
    }
}