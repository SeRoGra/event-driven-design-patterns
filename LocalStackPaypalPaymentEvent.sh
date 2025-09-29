aws sqs send-message \
  --message-body '{
      "type": "TRANSACTION",
      "subtype": "PAYMENT",
      "messageId": "MSG-PAYPAL-1192837465-03",
      "dni": "1045873921",
      "origin": "PAYPAL_WEBHOOK",
      "typeTransaction": "PAYMENT",
      "reference1": "PayPal",
      "reference2": "LINKED_BANK_XYZ",
      "reference3": "API",
      "reference4": "WEBHOOK",
      "paypalWebhookId": "WH-7e2a1f-5559",
      "event": {
        "eventType": "PAYMENT.CAPTURE.COMPLETED",
        "eventId": "PAYPAL-EVT-1192837465",
        "createTime": "2025-09-22T15:25:10Z",
        "resource": {
          "captureId": "3VV39562CJ5412345",
          "sellerProtection": "ELIGIBLE",
          "amount": {"currencyCode": "USD", "value": "150.75"},
          "exchange": {"to": "COP", "rate": 4025.50, "convertedValue": 607779.0},
          "payer": {
            "payerId": "PAYER123XYZ",
            "email": "user.paypal@example.com",
            "name": {"given_name": "Carlos", "surname": "Gómez"},
            "fundingSource": {"type": "BANK", "bankName": "Banco XYZ", "last4": "8876"}
          },
          "purchase_units": [
            {
              "reference_id": "PU-001",
              "shipping": {
                "name": "Carlos Gómez",
                "address": {"address_line_1":"Cra 12 # 45-67","admin_area_2":"Medellín","postal_code":"050021","country_code":"CO"}
              },
              "items": [
                {"name":"Curso Online NodeJS","sku":"COURSE-NODE-01","quantity":"1","unit_amount":{"currency_code":"USD","value":"120.00"}},
                {"name":"Certificado Digital","sku":"CERT-ADDON","quantity":"1","unit_amount":{"currency_code":"USD","value":"30.75"}}
              ]
            }
          ]
        }
      },
      "routing": {"ingestion": "paypal_webhook_adapter","priority": "HIGH"},
      "meta": {"receivedAt": "2025-09-22T15:25:12Z","ip": "186.30.11.75","userAgent": "PayPal-IPN/2.0"}
    }' \
    --queue-url "http://localhost:4566/000000000000/sqs-payment-events-local" \
    --endpoint-url=http://localhost:4566 \
    --region us-east-1

echo "PayPal message sent"