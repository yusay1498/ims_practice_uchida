variable "region" {
  description = "Region where to deploy the resources"
  default     = "ap-northeast-1"
  type        = string
}

variable "environment" {
  type        = string
  description = "The environment where to deploy the solution"
}

variable "env_label" {
  type        = string
}