terraform {
  required_version = ">= 0.15"
  required_providers {
    local = {
      source  = "hashicorp/local"
      version = "~> 2.0"
    }
  }
}

resource "local_file" "foo" {
  filename = "/home/ubuntu/foo.txt"
  content  = "bar-${var.environment}-${var.env_label}-${var.region}"
}