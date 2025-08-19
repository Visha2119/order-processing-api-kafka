package com.process.order_service.controller


import com.process.order_service.kafka.OrderProducer
import com.process.order_service.model.Order
import com.process.order_service.model.OrderRequest
import com.process.order_service.repository.OrderRepository

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")
class OrderController(
    private val producer: OrderProducer, private val orderRepository: OrderRepository
) {

    @PostMapping("/service")
    fun placeOrder(@RequestBody request: OrderRequest): ResponseEntity<String> {
        require(request.quantity > 0) { "Quantity must be greater than 0" }
        producer.sendOrder(request)
        return ResponseEntity.ok("Order placed successfully")
    }

    @GetMapping
    fun getOrders(@RequestParam(required = false) customerId: String?): ResponseEntity<List<Order>> {
        return ResponseEntity.ok(orderRepository.findAll(customerId))
    }
}
