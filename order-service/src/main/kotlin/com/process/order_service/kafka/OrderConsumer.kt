package com.process.order_service.kafka

import com.process.order_service.model.Order
import com.process.order_service.model.OrderRequest
import com.process.order_service.repository.OrderRepository
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class OrderConsumer (private val repo: OrderRepository) {
    @KafkaListener(topics = ["order-placed"], groupId = "order-service")
    fun consume(orderRequest: OrderRequest) {
        val orderId = Random.nextLong(1000, 9999)
        val order = Order(
            orderId = orderId,
            customerId = orderRequest.customerId,
            product = orderRequest.product,
            quantity = orderRequest.quantity, status = "RECEIVED"
        )
        repo.save(order)

        println("Order stored: $order")
    }
}