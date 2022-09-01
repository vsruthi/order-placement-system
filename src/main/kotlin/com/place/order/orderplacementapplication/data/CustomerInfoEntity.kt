package com.place.order.orderplacementapplication.data

import javax.persistence.*

@Entity
@Table(name= "CustomerInfo")
data class CustomerInfoEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val customerId : Long?,

    val firstName : String?,
    val lastName : String?,
    val phoneNumber : String?,
    val email :String?
)

