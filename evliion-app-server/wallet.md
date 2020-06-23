# Digital wallet - Powered by Marqeta
### Activate wallet
##### Request
```
curl -X POST "http://localhost:8080/wallet/activate" -H "accept: */*" -H "Authorization: Bearer <Jwt Token>"
```
##### Response
```
{
  "success": true,
  "message": "User wallet activated successfully"
}
```
### Add credit/debit card to the wallet
##### Request
```
curl -X POST "http://localhost:8080/wallet/fund_source/card" -H "accept: */*" -H "Authorization: Bearer <Jwt token>" -H "Content-Type: application/json" -d "{ \"accountNumber\": \"340000000000009\", \"cvvNumber\": \"432\", \"defaultAccount\": true, \"expMonth\": \"06\", \"expYear\": \"29\"}"
```
##### Response
```
{
  "success": true,
  "message": "Added card to user wallet successfully"
}
```
### Add billing address
##### Request
```
curl -X POST "http://localhost:8080/wallet/billing_address" -H "accept: */*" -H "Authorization: Bearer <Jwt token>" -H "Content-Type: application/json" -d "{ \"address1\": \"abc street\", \"address2\": \"Chennai\", \"city\": \"Chennai\", \"country\": \"India\", \"firstName\": \"Rajan\", \"lastName\": \"Roy\", \"postalCode\": \"640113\", \"state\": \"TN\"}"
```
##### Response
```
{
  "success": true,
  "message": "Billing address added successfully"
}
```
### Get wallet details
Use this api, to get the fund source (card) id and the billing address id
##### Request
```
curl -X GET "http://localhost:8080/wallet/" -H "accept: */*" -H "Authorization: Bearer <Jwt token>"
```
##### Response
```
{
  "fundSources": [
    {
      "createdTime": "2020-06-21T01:51:06Z",
      "lastModifiedTime": "2020-06-21T01:51:06Z",
      "type": "paymentcard",
      "id": "32220b99-8dc0-4c59-adde-376f2feccd6b",
      "accountSuffix": "0009",
      "expDate": "0629",
      "nameOnAccount": null
    }
  ],
  "billingAddresses": [
    {
      "firstName": "Rajan",
      "lastName": "Roy",
      "address1": "abc street",
      "address2": "Chennai",
      "city": "Chennai",
      "state": "TN",
      "zip": "640113",
      "postalCode": null,
      "country": "India",
      "id": "64266aea-e125-4152-8bae-dc6dabbb6630"
    }
  ]
}
```
### Recharge wallet
##### Request
```
curl -X POST "http://localhost:8080/wallet/recharge" -H "accept: */*" -H "Authorization: Bearer <Jwt token>" -H "Content-Type: application/json" -d "{ \"amount\": 100, \"billingAddressId\": \"64266aea-e125-4152-8bae-dc6dabbb6630\", \"currency\": \"USD\", \"fundSourceId\": \"32220b99-8dc0-4c59-adde-376f2feccd6b\"}"
```

##### Response
```
{
  "success": true,
  "message": "User wallet recharged successfully"
}
```

### Check wallet balance
##### Request
```
curl -X GET "http://localhost:8080/wallet/balance" -H "accept: */*" -H "Authorization: Bearer <Jwt token>"
```
##### Response
```
{
  "gpa": {
    "balances": {
      "USD": {
        "balances": null,
        "available_balance": 80.5,
        "ledger_balance": 80.5,
        "currency_code": "USD",
        "credit_balance": 0,
        "pending_credits": 0
      }
    },
    "available_balance": 80.5,
    "ledger_balance": 80.5,
    "currency_code": "USD",
    "credit_balance": 0,
    "pending_credits": 0
  },
  "error_code": null,
  "error_message": null
}
```
### Transact 
##### Request
```
curl -X POST "http://localhost:8080/wallet/transact" -H "accept: */*" -H "Authorization: Bearer <Jwt token>" -H "Content-Type: application/json" -d "{ \"amount\": 10, \"currency\": \"USD\", \"merchantId\": 2}"
```

##### Response
```
{
  "success": true,
  "message": "UserWallet transacted successfully"
}
```