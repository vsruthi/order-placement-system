package com.place.order.orderplacementapplication.data.repo

import com.place.order.orderplacementapplication.data.AddressEntity
import org.springframework.data.repository.CrudRepository

interface AddressRepository : CrudRepository <AddressEntity, Long> {

}