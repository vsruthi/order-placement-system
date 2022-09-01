package com.place.order.orderplacementapplication.data.repo

import com.place.order.orderplacementapplication.data.OrderDetailsEntity
import org.springframework.data.repository.CrudRepository

interface OrderDetailsRepository : CrudRepository<OrderDetailsEntity, Long> {
}
