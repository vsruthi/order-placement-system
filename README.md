# order-placement-system
Rest Api for adding/viewing/deleting orders(Kotlin/Spring boot)

An application that a sales consultant working at a moving company will use to place / find / edit / delete orders on behalf of customers.
The company that the sales consultant works at offers these services for their customers:
-Moving
-Packing
-Cleaning

When placing an order, the sales consultant will need the following information from the customer:
-Name
-Phone number
-Email
-Address he’s moving from
-Address he’s moving to
-What service they are ordering
-Date for when the service is to be carried out
-A text field where the sales consultant can add a note to the order, for example: “the customer won’t be available till 12 pm)


User Stories:
-As a sales consultant, I want to place an order for a customer. 

-As a sales consultant, I want to find an order that I’ve previously placed for a customer. 

-As a sales consultant, I want to delete an order that I’ve previously placed for a customer. 

Service API
An API (REST service) is developed that satisfies the user stories and does the business logic using Kotlin, Springboot. The database used for this is H2 which is Springboot's in memory data base.

Once the application is up, the database can be accessed using the url - http://localhost:8080/h2-console.
Tables

-CUSTOMER_INFO

-ORDER_DETAILS

-ADDRESS

Apis can be accessed from 'postman' using the following url and sample request for 'save' is also provided.
http://localhost:8080/order/save
Sample request for 'save'

{
    "customerInfo": {
        "firstName": "Deepak",
        "lastName": "vadakkoot",
        "phoneNumber": "23456723",
        "email": "dev@qwe.com"
    },
    "orderType": "Packing",
    "fromAddress": {
        "houseNumber": "07",
        "city": "London",
        "zipCode": "6803",
        "country": "UK",
        "streetName": "t.k.road"
    },
    "toAddress": {
        "houseNumber": "27",
        "city": "Manchester",
        "zipCode": "6803",
        "country": "UK",
        "streetName": "t.k.road"
    },
    "orderDate": "2022-12-24",
    "message": "Please be careful",
    "createdDate": null,
    "modifiedDate": null
}


http://localhost:8080/order/view/2 (Where 2 is the order id to be deleted)

http://localhost:8080/order/delete/1? (Where 1 is the order id to be deleted)

Sequence diagram

![Sequence diagram](https://user-images.githubusercontent.com/9262657/188100394-211d96b4-5b12-4f6b-a6bb-c17d552e3df8.jpg)


Class Diagram

![class diagram](https://user-images.githubusercontent.com/9262657/188100510-8b31bb51-2810-4b79-82b1-5b2401c8d18a.jpg)





