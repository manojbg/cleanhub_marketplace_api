# CleanHub Marketplace API Service
## Introduction : 
This springboot service exposes REST API's about cleanhub customers/companies.

## Contents :
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Features](#features)
* [To do](#to_do)

## General info :
CleanHub Marketplace API provides 2 public APIs:

Customer logos can be used to retrieve all the CleanHub active customers:

    GET https://marketplace.cleanhub.com/api/public/orders/logos

Customer orders can be retrieved using below api:

    GET https://marketplace.cleanhub.com/api/public/orders?route={customerRoute}

This Springboot service does the following:
1. Use the CleanHub Marketplace API to fetch customer orders
2. Periodically fetch the data from CleanHub Marketplace API to build up the history
3. Implements a RESTful API that fetches the top X customers by the quantity
   increased in the specified time interval

## Technologies :

* Java 17
* SpringBoot 3.x
* Postgresql 10
* Docker 
* Maven 
* Swagger

## Setup :

* Install Java 17 on your machine, clone the project cleanhub_marketplace_api
        
        git clone https://github.com/manojbg/cleanhub_marketplace_api.git
* Import project to IntelliJ or eclipse. Build the project :

        mvn clean install
* There are two ways to run the application
1. Docker : 
    Using ```docker-compose up``` command. This builds two docker images. One for Postgresql connection and other for SpringBootApplication.
    
2. As a Spring application : Run project as Spring application. This requires a Prerequisite of creating Postgresql database connection and tables. Details can be found in init.db and application.properties.


API details can be found : http://localhost:8080/swagger-ui/index.html#

## Features :
1. Using the CleanHub Marketplace API to fetch customer orders :

Request URL:
    ```http://localhost:8080/api/v1/cleanhubmarketplace/companyOrders```

Response :

    [
      {
        "companyName": "GUYOT Team Europe",
        "landingPageRoute": "guyot",
        "state": "IN_PROGRESS",
        "quantity": 20000,
        "recoveredQuantity": 13205.479452,
        "contractStartDate": "2023-05-01T00:00:00.000+00:00",
        "uuid": "df1a1c04-4c9a-4445-9e4f-c1c0b7b119ee"
      },
      {
        "companyName": "Digital Life Design Conference",
        "landingPageRoute": "dld",
        "state": "IN_PROGRESS",
        "quantity": 1043,
        "recoveredQuantity": 260.43,
        "contractStartDate": "2023-10-05T00:00:00.000+00:00",
        "uuid": "127d0ccc-999e-467d-9c5f-9b7161dd959c"
      }
    ]

2. Using the CleanHub Marketplace API to fetch customers: 

Request URL:
        ```http://localhost:8080/api/v1/cleanhubmarketplace/companies```

Response :

    [
        {
            "companyName": "string",
            "landingPageRoute": "string",
            "state": "string",
            "quantity": 0,
            "recoveredQuantity": 0,
            "contractStartDate": "2024-01-02T23:36:20.313Z",
            "uuid": "3fa85f64-5717-4562-b3fc-2c963f66afa6"
        }
    ]

2. Periodically fetch the data from CleanHub Marketplace API to build up the history :

Scheduler runs every 10 mins to fetch the data to build up the history.

3. API is exposed to show the history of the above scheduler:

Request Url :
```http://localhost:8080/api/v1/cleanhubmarketplace/companyhistory?landingPageRoute=foamie```

Response :

        [
            {
            "companyName": "Foamie",
            "landingPageRoute": "foamie",
            "state": "IN_PROGRESS",
            "quantity": 80057.2191,
            "contracts": 
                [
                    {
                        "landingPageRoute": "foamie",
                        "createdAt": "2021-11-23T09:30:18.444+00:00",
                        "startDate": "2020-12-01T00:00:00.000+00:00",
                        "endDate": "2021-05-30T00:00:00.000+00:00",
                        "period": "YEARLY",
                        "quantity": 27000,
                        "recoveredQuantity": 27000,
                        "type": "INITIAL",
                        "uuid": "ae33065f-e042-40a6-9ade-d51baa30fccb",
                        "fulfilled": true
                    }
                ]
            }
        ]


4. Implements a RESTful API that fetches the top X customers by the quantity
   increased in the specified time interval

Request Url:
```http://localhost:8080/api/v1/cleanhubmarketplace/topCompanies?topX=10&startDate=2022-10-10&endDate=2022-11-11```

Response :

    [
        {
            "companyName": "Newton",
            "landingPageRoute": "newton",
            "state": "FULFILLED",
            "quantity": 1000000,
            "recoveredQuantity": 1000000,
            "contractStartDate": "2022-11-01T00:00:00.000+00:00",
            "uuid": "956e8d00-0b19-446a-8757-f0e6023902f0"
        }
    ]
## To do :
* Test cases coverage
* Asynchronous calls
* Better validation and error handling (customize exceptions)

