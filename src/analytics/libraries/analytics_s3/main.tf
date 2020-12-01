locals {
  identifier_prefix = "${terraform.workspace}-${var.name}"
  tags = merge(var.tags, {
    Resource = "s3"
  })
}

resource "aws_s3_bucket" "this" {
  bucket = local.identifier_prefix
  acl    = "private"

  force_destroy = var.force_destroy_s3_buckets

  tags = local.tags

  versioning {
    enabled = false
  }

  server_side_encryption_configuration {
    rule {
      apply_server_side_encryption_by_default {
        sse_algorithm = "AES256"
      }
    }
  }

  logging {
    target_bucket = var.logs_bucket_id
    target_prefix = "${local.identifier_prefix}/"
  }
}

resource "aws_s3_bucket_public_access_block" "this" {
  bucket = aws_s3_bucket.this.id

  block_public_acls       = true
  block_public_policy     = true
  ignore_public_acls      = true
  restrict_public_buckets = true
}

data "aws_iam_policy_document" "this" {
  statement {
    actions = ["s3:*"]
    principals {
      type        = "AWS"
      identifiers = ["*"]
    }
    resources = ["${aws_s3_bucket.this.arn}/*"]

    effect = "Deny"

    condition {
      test     = "Bool"
      values   = ["false"]
      variable = "aws:SecureTransport"
    }
  }
}

resource "aws_s3_bucket_policy" "this" {
  # in terraform v0.12.29 we encounter conflict when this is executed concurrently with setting public access block
  depends_on = [aws_s3_bucket_public_access_block.this]
  bucket     = aws_s3_bucket.this.id
  policy     = data.aws_iam_policy_document.this.json
}
