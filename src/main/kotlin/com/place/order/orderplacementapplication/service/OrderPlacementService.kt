package com.place.order.orderplacementapplication.service

import com.place.order.orderplacementapplication.model.Order
import com.place.order.orderplacementapplication.model.request.SaveOrderRequest

interface OrderPlacementService {
    fun saveOrder(orderRequest: Order): String
    fun viewOrder(orderId: Long): Any?
    fun updateOrder(): String
    fun deleteOrder(orderId: String): String

}