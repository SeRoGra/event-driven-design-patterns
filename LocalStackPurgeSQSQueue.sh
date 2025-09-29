aws sqs purge-queue \
  --queue-url "sqs-payment-events-local" \
  --endpoint-url http://localhost:4566 \
  --region us-east-1
