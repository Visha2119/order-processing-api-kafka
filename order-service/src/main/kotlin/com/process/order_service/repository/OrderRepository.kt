package com.process.order_service.repository


import com.process.order_service.model.Order
import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap

@Repository
class OrderRepository {

    private val orders = ConcurrentHashMap<Long, Order>()

    fun save(order: Order) {
        orders[order.orderId] = order
    }

    fun findAll(customerId: String? = null): List<Order> =
        orders.values.filter { customerId == null || it.customerId == customerId }
}