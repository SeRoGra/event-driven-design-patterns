aws --endpoint-url=http://localhost:4566 --region us-east-1 sqs send-message \
  --queue-url "http://localhost:4566/000000000000/sqs-payment-events-local" \
  --message-body '{
    "reference1": "PhysicalCard",
    "reference2": "XYZ_VISA",
    "reference3": "MOBILE",
    "reference4": "ONE_CLICK",
    "eventId": "EVT-CARD-9b7c1e0a",
    "payment": {
      "method": "CreditCard",
      "amount": {"currency": "COP", "total": 780000},
      "installments": 3
    },
    "card": {
      "network": "VISA",
      "token": "tok_visa_4fae1b9c",
      "last4": "1234",
      "holder": "María Rodríguez",
      "expiry": "12/28",
      "issuerBank": "Banco XYZ"
    },
    "auth": {
      "mode": "3DS2",
      "status": "CHALLENGE_REQUIRED",
      "acsUrl": "https://acs.xyzbank.com/challenge",
      "transactionId": "3ds2-tx-41a0bb7f",
      "eci": "07"
    },
    "merchant": {
      "merchantId": "MER-44129",
      "mcc": "5732",
      "name": "Tienda MusiTech"
    },
    "billing": {
      "addressLine": "Calle 45 # 20-11",
      "city": "Bogotá",
      "postalCode": "110231",
      "country": "CO"
    },
    "fraud": {
      "riskScore": 0.17,
      "rulesTriggered": ["velocity_low", "device_known"],
      "deviceFingerprint": "dfp-8c2f7b"
    },
    "items": [
      {"sku":"GUIT-STR-01","desc":"Cuerdas guitarra","qty":2,"unitPrice":45000},
      {"sku":"AMP-PRT-10","desc":"Amplificador portátil","qty":1,"unitPrice":690000}
    ],
    "channelInfo": {
      "platform": "ANDROID",
      "appVersion": "2.14.3",
      "ip": "181.60.45.200"
    },
    "createdAt": "2025-09-22T15:22:31Z",
    "status": "AUTHORIZED_PENDING_CAPTURE"
  }'

echo "PhysicalCard message sent"