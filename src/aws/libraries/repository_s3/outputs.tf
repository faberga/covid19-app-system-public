output "bucket_name" {
  value = aws_s3_bucket.this.bucket
}

output "bucket_arn" {
  value = aws_s3_bucket.this.arn
}

output "lambda_object_key" {
  value = aws_s3_bucket_object.lambda.id
}
