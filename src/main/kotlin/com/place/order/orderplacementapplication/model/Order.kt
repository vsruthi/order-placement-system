package com.place.order.orderplacementapplication.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDate
import java.util.*

data class Order(
    val customerInfo: CustomerInfo? = null,
// whether its cleaning packing or moving
    val orderType: String? = null,
    val fromAddress: Address? = null,

// Applicable only for moving
    val toAddress: Address? = null,
    val orderDate: LocalDate? = null,
    val message: String? = null,
    val createdDate: LocalDate?,
    val modifiedDate: LocalDate?

)




