package com.place.order.orderplacementapplication.data

import javax.persistence.*

@Entity
@Table(name= "Address")
data class AddressEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Int?,
    val houseNumber:String? = null,
    val StreetName:String? = null,
    val city:String? = null,
    val zipCode:String? = null,
    val country:String? = null

)
