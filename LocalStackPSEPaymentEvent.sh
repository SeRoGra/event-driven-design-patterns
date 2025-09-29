aws sqs send-message \
  --message-body '{
      "type": "TRANSACTION",
      "subtype": "PAYMENT",
      "messageId": "MSG-PSE-1f2a9c7e-01",
      "dni": "1020304050",
      "origin": "PSE_GATEWAY",
      "typeTransaction": "PAYMENT",
      "reference1": "PSE",
      "reference2": "BANK_XYZ",
      "reference3": "WEB",
      "reference4": "CHECKOUT_V2",
      "flowId": "FLOW-PSE-1f2a9c7e",
      "paymentMethod": "PSE",
      "pse": {
        "selectedBankCode": "XYZ001",
        "userType": "PERSONA_NATURAL",
        "agreement": {
          "termsVersion": "v3.4.1",
          "consentGiven": true,
          "consentAt": "2025-09-22T15:20:05Z",
          "ip": "190.85.32.101"
        },
        "redirectUrls": {
          "successUrl": "https://merchant.example.com/return/pse/success",
          "failureUrl": "https://merchant.example.com/return/pse/failure"
        }
      },
      "customer": {
        "fullName": "Juan Pérez",
        "docType": "CC",
        "docNumber": "1020304050",
        "email": "juan.perez@example.com",
        "phone": "+57-3001234567"
      },
      "order": {
        "orderId": "ORD-784512",
        "items": [
          {"sku": "SKU-ABC-01", "name": "Suscripción Premium", "qty": 1, "unitPrice": 250000}
        ],
        "amount": {"currency": "COP", "total": 250000}
      },
      "session": {
        "deviceId": "dev-5e1a2c",
        "channel": "WEB",
        "userAgent": "Safari/605.1.15",
        "geo": {"country": "CO", "city": "Bogotá"}
      },
      "createdAt": "2025-09-22T15:20:05Z",
      "status": "PENDING_AUTHORIZATION"
    }' \
    --queue-url "http://localhost:4566/000000000000/sqs-payment-events-local" \
    --endpoint-url=http://localhost:4566 \
    --region us-east-1

echo "PSE message sent"