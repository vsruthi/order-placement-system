package com.place.order.orderplacementapplication.controller

import com.place.order.orderplacementapplication.model.request.SaveOrderRequest
import com.place.order.orderplacementapplication.service.OrderPlacementService
import com.place.order.orderplacementapplication.model.Order
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.print.attribute.standard.ReferenceUriSchemesSupported.HTTP


/**
 * The controller class to serve order apis

 */
@RestController
@RequestMapping("/order")
class OrderPlacementController(val service: OrderPlacementService) {

    val logger: Logger = LogManager.getLogger(this::class.java)

    /**
     * Methods fetches particular order details
     *
     */
    @GetMapping("/view/{orderId}")
    fun viewOrder(
        @PathVariable orderId: String
    ): Any? {
        logger.traceEntry("Entering save order", orderId)
        return service.viewOrder(orderId.toLong())
    }

    /**
     * Method saves an order details
     * @param requestOrder - Order submitted on behalf of customer
     * @return success if the order saved successfully
     */

    @PostMapping("/save")
    fun saveOrder(
        @RequestBody requestOrder: Order
    ): String? {
        logger.traceEntry("Entering save order", requestOrder)
        return service.saveOrder(requestOrder)
    }

    /**
     * Method deletes an order
     * @param OrderId - id of the order to be deleted
     *
     */
    @DeleteMapping("/delete/{orderId}")
    fun deleteOrder(
        @PathVariable orderId: String
    ): HttpStatus{
        logger.traceEntry("Entering deleteOrder", orderId)
        val status : String = service.deleteOrder(orderId)
        if (status == "success")
            return HttpStatus.OK
        else
            return HttpStatus.INTERNAL_SERVER_ERROR
    }

    /**
     * Method modifies a specific order details
     */
    @PostMapping("/update")
    fun updateOrder(): Order? {
        //todo
        return null
    }
}