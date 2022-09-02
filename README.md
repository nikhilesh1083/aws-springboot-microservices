# aws-springboot-microservices
poc project using aws ,microservices and springboot technology.


Scenario - 

1.	Create a csv file (ProductId,ProductType,ProductName,Price) and upload it in the S3 bucket. Then write a lambda which will read this CSV file from S3 bucket as soon as it is uploaded and it will process the record and save in the Dynamo DB with productid as the hashKey.
    a.	Create at least 100 records in the CSV.
    b.	Products can be electronic items.
    c.	ProductType can be Laptop, Mobile, etc
2.	Create a Product Microservice. This microservice connect to the above Dynamo DB to retrieve the products. 
     a.	Create an end point GET /api/v1/product/getAllProducts – This gets all the product from the Dynamo DB. Response would be List<ProductDetails>. ProductDetails           object contains (productid, productType, productName and Price)
     b.	Create an end point GET /api/v1/product/getProducts?productId = <<productId>>. Response would be ProductDetails.
     c.	Use AWS SDK to connect to DynamoDB and to retrieve the products.
     d.	Expose the end point using Spring cloud gateway server.
     e.	Create a Rating as a config in config server for some of the product Ids, for those whose config is not present will default to no rating.
     f.	Service discovery be Eureka
3.	Create Basket MS. This Basket Microservice will connect to another Dynamo DB (Basket) to store and create the basket element with TTL of 30 days.
    a.	Create end point POST api/v1/basket/addProducts. This takes Product Id as the request body. Internally it will call /api/v1/product/getProducts?productId=            <<productId>> API and retrieve the product information from the Product Microservices. This API should be called using FeginClient instead RestTemplate. Returns          201 OK for successful response along with basketId.
    i.	Implement circuit breaker for the above REST call using Reselience 4j.
    b.	Create end point GET api/v1/basket/getBasketById?basketId = <basketId>. This API will query the DynamoDB using basketId and return the basket object which            contains productid, productname and price. This will be called in checkout journey.
4.	Create checkout MS. This will create the order and publish in the queue for processing.
    a.	Create end point POST / api/v1/checkout/createCheckout. This will take DeliveryDetails object, basketId. It internally calls /api/v1/basket/getBasketById?            basketId= API of above and gets the basket object and put the order details containing the above Basket object and DeliveryDetails object in SQS.
    b.	Create a consumer service which will listen to this queue, process the order and send the orderId using SNS.
