package com.place.order.orderplacementapplication.service

import com.place.order.orderplacementapplication.data.AddressEntity
import com.place.order.orderplacementapplication.data.CustomerInfoEntity
import com.place.order.orderplacementapplication.data.OrderDetailsEntity
import com.place.order.orderplacementapplication.data.repo.AddressRepository
import com.place.order.orderplacementapplication.data.repo.CustomerInfoRepository
import com.place.order.orderplacementapplication.data.repo.OrderDetailsRepository
import com.place.order.orderplacementapplication.model.Address
import com.place.order.orderplacementapplication.model.CustomerInfo
import com.place.order.orderplacementapplication.model.Order
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*


@Service
class OrderPlacementServiceImpl(
    val addressRepository: AddressRepository,
    val customerInfoRepository: CustomerInfoRepository,
    val orderDetailsRepository: OrderDetailsRepository
) : OrderPlacementService {

    val logger: Logger = LogManager.getLogger(this::class.java)

    /**
     * Method saves an order details
     * @param orderRequest - Order submitted on behalf of customer
     * @return success message if the order saved successfully
     */
    override fun saveOrder(orderRequest: Order): String {
        try {
            val status: String? = populateAndSaveOrderDetails(orderRequest)
        } catch (e: Exception) {
            logger.error("Exception while saving order $e", e.printStackTrace())
        }
        return "Order Created Successfully"
    }

    /**
     * Method populate Entities And Save OrderDetails to database
     *
     * @param orderRequest - Order submitted on behalf of customer
     * @return success message if the order saved successfully
     */
    private fun populateAndSaveOrderDetails(orderRequest: Order): String {
        val date: LocalDate = LocalDate.now()
        val order: Order? = null
        if (orderRequest != null) {


            val fromAddressEntity: AddressEntity = AddressEntity(
                null,
                orderRequest.fromAddress?.houseNumber,
                orderRequest.fromAddress?.StreetName,
                orderRequest.fromAddress?.city,
                orderRequest.fromAddress?.zipCode,
                orderRequest.fromAddress?.country
            )
            //Todo To Address is valid only when order type = "Moving"
           // if(orderRequest.orderType.equals("Moving")) {
                val toAddressEntity2: AddressEntity = AddressEntity(
                    null,
                    orderRequest.toAddress?.houseNumber,
                    orderRequest.toAddress?.StreetName,
                    orderRequest.toAddress?.city,
                    orderRequest.toAddress?.zipCode,
                    orderRequest.toAddress?.country
                )
         //   }
            val customerInfoEntity: CustomerInfoEntity =
                CustomerInfoEntity(
                    null,
                    orderRequest.customerInfo?.firstName,
                    orderRequest.customerInfo?.lastName,
                    orderRequest.customerInfo?.phoneNumber,
                    orderRequest.customerInfo?.email
                )

            val orderEntity: OrderDetailsEntity = OrderDetailsEntity(
                null,
                orderRequest.orderType,
                fromAddressEntity,
                toAddressEntity2,
                orderRequest.orderDate,
                orderRequest.message,
                date,
                date,
                customerInfoEntity
            )

            //saving order into the database
            val status = orderDetailsRepository.save(orderEntity)
            logger.info("exiting populateOrderDetails status---------- {$status}");
        }
        return "Saved order successfully"
    }

    /**
     * Method returns order specified by orderId
     *
     * @param orderId submitted on behalf of customer
     * @return Order details if the orderId is valid
     */
    override fun viewOrder(orderId: Long): Any? {
        var order: Order? = null
        var orderDetailsOpt: Optional<OrderDetailsEntity> ?= null
        var statusMessage : String = ""
        try {
            if (orderId!= null && !orderId.equals("")) {
                 orderDetailsOpt = orderDetailsRepository.findById(orderId)

                val orderObj = orderDetailsOpt.get()
                if(orderObj!= null) {
                    order = populateView(orderObj)
                    logger.info("viewOrder orderDetailsOpt.......... {$orderDetailsOpt}");
                }
            }
            else{
                logger.warn("Invalid or empty orderID")
            }
        }catch (e:NoSuchElementException) {
            logger.error("Invalid order id  $e", e.printStackTrace())
            return orderDetailsOpt
        }
        catch (e: Exception) {
            logger.error("Exception while viewOrder  $e", e.printStackTrace())
        }
        return orderDetailsOpt
    }

    /**
     * Method populates values into Order Model from OrderDetailsEntity
     *
     * @param Order Details Entity
     * @return Order Model
     */
    private fun populateView(orderObj: OrderDetailsEntity): Order? {
        var order: Order? = null;
        var customerInfo: CustomerInfo? = null;
        var fromAddress: Address? = null;
        var toAddress: Address? = null;
        if (orderObj.customerInfoEntity != null) {
            customerInfo = CustomerInfo(
                orderObj.customerInfoEntity.firstName,
                orderObj.customerInfoEntity.lastName,
                orderObj.customerInfoEntity.phoneNumber, orderObj.customerInfoEntity.email
            )
        }
        if (orderObj.fromAddressEntity != null) {
            fromAddress = Address(
                orderObj.fromAddressEntity.houseNumber, orderObj.fromAddressEntity.StreetName,
                orderObj.fromAddressEntity.city, orderObj.fromAddressEntity.zipCode,
                orderObj.fromAddressEntity.country
            )
        }

        if (orderObj.toAddressEntity != null) {
            toAddress = Address(
                orderObj.toAddressEntity.houseNumber, orderObj.toAddressEntity.StreetName,
                orderObj.toAddressEntity.city, orderObj.toAddressEntity.zipCode,
                orderObj.toAddressEntity.country
            )
        }
        order = Order(
            customerInfo,
            orderObj.orderType,
            fromAddress,
            toAddress,
            orderObj.orderDate,
            orderObj.message,
            orderObj.createdDate,
            orderObj.modifiedDate
        )
        return order
    }

    /**
     * Method deletes an order if orderId is valid
     * @param OrderId - id of the order to be deleted
     *
     */
    override fun deleteOrder(orderId: String): String {
        var statusMessage: String = ""
        statusMessage = if (orderId != null && orderId != "") {
            try {
                val status = orderDetailsRepository.deleteById(orderId.toLong())
                println("status after deletion {$status}")

            } catch (e: Exception) {
                logger.error("Exception while viewOrder  $e", e.printStackTrace())
            }
            "success"
        } else {
            "Invalid or empty Order id"
        }
        return statusMessage
    }


    override fun updateOrder(): String {
        TODO("Not yet implemented")
    }

}