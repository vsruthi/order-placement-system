package com.place.order.orderplacementapplication.data

import com.place.order.orderplacementapplication.model.CustomerInfo
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "OrderDetails")
data class OrderDetailsEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val orderId: Long?,
    val orderType: String?,

    // fromAddressId
    @OneToOne(cascade = arrayOf(CascadeType.ALL))
    @JoinColumn(name = "fromId", referencedColumnName = "id")
    val fromAddressEntity: AddressEntity,

    // toAddressId
    @OneToOne(cascade = arrayOf(CascadeType.ALL))
    @JoinColumn(name = "toId", referencedColumnName = "id")
    val toAddressEntity: AddressEntity,

    val orderDate: LocalDate?,

    val message: String?,
    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    var createdDate: LocalDate,


    val modifiedDate: LocalDate,
    @OneToOne(cascade = arrayOf(CascadeType.ALL))
    @JoinColumn(name = "customerId", referencedColumnName = "customerId")
    val customerInfoEntity: CustomerInfoEntity

)
