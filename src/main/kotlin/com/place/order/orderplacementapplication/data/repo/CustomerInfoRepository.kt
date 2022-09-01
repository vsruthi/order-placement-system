package com.place.order.orderplacementapplication.data.repo

import com.place.order.orderplacementapplication.data.AddressEntity
import com.place.order.orderplacementapplication.data.CustomerInfoEntity
import org.springframework.data.repository.CrudRepository

interface CustomerInfoRepository : CrudRepository<CustomerInfoEntity, Long>  {
}