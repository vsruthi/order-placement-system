package com.place.order.orderplacementapplication.model.request

import com.place.order.orderplacementapplication.model.Order

data class SaveOrderRequest(
    val order:Order,
    val orderType : String
)
