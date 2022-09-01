create table CustomerInfo(
	customerId bigint not null,
	firstName varchar(255) not null,
	lastName varchar(255) not null,
	phoneNumber varchar(255) not null,
	email varchar(255) not null,
	primary key(customerId)
);

create table address(
id bigint not null,
houseNumber varchar(25),
StreetName varchar(40),
city varchar(40),
zipCode varchar(5),
country varchar(40),
PRIMARY KEY (id)
);

create table orderDetails(
orderId bigint not null,
orderType varchar(5),
fromAddressId bigInt not null,
toAddressId bigInt,
orderDate date,
customerId bigint,
message varchar(255),
createdDate Date,
modifiedDate Date,

 FOREIGN KEY (fromAddressId) REFERENCES Address(id),
 PRIMARY KEY (OrderID),
 FOREIGN KEY (customerId) REFERENCES CustomerInfo(customerId)

);
