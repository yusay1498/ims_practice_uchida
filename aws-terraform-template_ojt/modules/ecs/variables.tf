variable "region" {
  type = string
}

variable "ecs_cluster_name" {
  type = string
}

variable "ecs_api_taskdef_name" {
  type = string
}

variable "ecs_api_taskdef_exec_role_arn" {
  type = string
}

variable "ecs_api_taskdef_container_name" {
  type = string
}

variable "ecs_api_taskdef_image_tag" {
  type = string
}

variable "ecs_api_taskdef_db_endpoint" {
  type = string
}

variable "ecs_api_taskdef_db_name" {
  type = string
}

variable "ecs_api_taskdef_db_option" {
  type = string
}

variable "ecs_api_taskdef_db_username_value_from" {
  type = string
}

variable "ecs_api_taskdef_db_password_value_from" {
  type = string
}
