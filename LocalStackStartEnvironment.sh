# Create localstack docker instance
docker run -d --rm --name localstack-local -p 4566:4566 localstack/localstack

# Generate a new SQS Queue
aws sqs create-queue \
  --queue-name sqs-payment-events-local \
  --endpoint-url http://localhost:4566 \
  --region us-east-1