## Scenario: Add credit/debit card 
## Mockup : #18-Payment page
## Assigned To : Kumar Swamy

```bash
POST https://localhost:8080/api/v1/payment-method 

Request
{
“userId”: 123,
“cardNumber”: 1234 5678 456,
“expiryYear”: 2020,
“expiryMonth”: “Dec”, 
 “nameOnCard”: “Kumar Swamy”
}

Response
Success response
{
“success”: “true”,
 “message”: ”Added successfully”
}

Fail response
{
“success”: “false”,
“message”: “Failed creating payment method. Please try after some time”
}
```

## Scenario: Get single card details
## Mockup: TBA
## Assigned To : Kumar Swamy

```bash
GET https://localhost:8080/api/v1/payment-method/{paymentMethodId}

Response:
Success Response:
{
“paymentMethodId”: 444123, 
 “userId”: 123,
“cardNumber”: 1234 5678 456,
“expiryYear”: 2020,
“expiryMonth”: “Dec”, 
 “nameOnCard”: “Kumar Swamy”
}

Fail response
{
“success”: “false”,
“message”: “Failed fetching card details. Please try after some time”
}
```

## Scenario: Get multiple card details 
## Mockup: TBA
## Assigned To : Kumar Swamy

```bash
GET https://localhost:8080/api/v1/payment-methods/{userId}

Response:
Success Response:
{
“userId”: 123,
“paymentMethods”:
[{
“paymentMethodId”: 444123, 
 “cardNumber”: 1234 5678 456,
“expiryYear”: 2020,
“expiryMonth”: “Dec”, 
 “nameOnCard”: “Kumar Swamy”
},
{
“paymentMethodId”: 466366, 
 “cardNumber”: 5555 7575 5757,
“expiryYear”: 2021,
“expiryMonth”: “Dec”, 
 “nameOnCard”: “Amit Dandawate”
}]
}

Fail response
{
“success”: “false”,
“message”: “Failed fetching payment methods. Please try after some time”,
}
```


## Scenario: Add Charge point
## Mockup : TBA
## Assigned To : Kumar Swamy

```bash
POST https://localhost:8080/api/v1/charge-point 

Request
{
“stationId”: 123,
“chargePointIdentifier”: “A-1234”,
“capacity”: 2020,
“type”: “Slow/Fast”
}

Response
Success response
{
“success”: “true”,
 “message”: ”Added successfully”
}

Fail response
{
“success”: “false”,
“message”: “Failed, please try after sometime”
}
```

## Scenario: Get single charge point details
## Mockup: TBA
## Assigned To : Kumar Swamy

```bash
GET https://localhost:8080/api/v1/charge-point/{chargePointId}

Response:
Success Response:
{
“stationId”: 123,
“chargePointId”: 654849068,
“chargePointIdentifier”: “A-1234”,
“capacity”: 2020,
“type”: “Slow/Fast”,
“status”: “Active/Suspended”
}

Fail response
{
“success”: “false”,
“message”: “Failed fetching charge point details. Please try after some time”
}
```

## Scenario: Get all charge points for given statin Id 
## Mockup: TBA
## Assigned To : Kumar Swamy

```bash
GET https://localhost:8080/api/v1/charge-points/{stationId}

Response:
Success Response:
{
“stationId”: 123,
“chargePoints”:
[{
 “chargePointId”: 654849068,
“chargePointIdentifier”: “A-1234”,
“capacity”: 2020,
“type”: “Fast”,
“status”: “Active”
},
{
“chargePointId”: 6943060,
“chargePointIdentifier”: “A-3535”,
“capacity”: 2020,
“type”: “Slow”,
“status”: “Suspended”
}]
}

Fail response
{
“success”: “false”,
“message”: “Failed fetching payment methods. Please try after some time”,
}
```
