# https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/ecs_cluster
resource "aws_ecs_cluster" "main" {
  name = var.ecs_cluster_name

  # setting {
  #   name  = "containerInsights"
  #   value = "enabled"
  # }
}

# https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/ecr_repository
resource "aws_ecr_repository" "main_api" {
  name = var.ecs_api_taskdef_name
  image_tag_mutability = "MUTABLE"

  image_scanning_configuration {
    scan_on_push = true
  }
}

# https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/ecs_task_definition
resource "aws_ecs_task_definition" "main_api" {
  family                   = var.ecs_api_taskdef_name
  requires_compatibilities = ["FARGATE"]
  network_mode             = "awsvpc"
  cpu                      = 1024
  memory                   = 2048
  execution_role_arn       = var.ecs_api_taskdef_exec_role_arn
  container_definitions    = <<TASKDEF
  [
      {
          "name": "${var.ecs_api_taskdef_container_name}",
          "image": "${aws_ecr_repository.main_api.repository_url}:${var.ecs_api_taskdef_image_tag}",
          "cpu": 0,
          "portMappings": [
              {
                  "name": "api-8080-tcp",
                  "containerPort": 8080,
                  "hostPort": 8080,
                  "protocol": "tcp",
                  "appProtocol": "http"
              }
          ],
          "essential": true,
          "environment": [
              {
                  "name": "SPRING_DATASOURCE_URL",
                  "value": "jdbc:postgresql://${var.ecs_api_taskdef_db_endpoint}/${var.ecs_api_taskdef_db_name}${var.ecs_api_taskdef_db_option}"
              }
          ],
          "environmentFiles": [],
          "mountPoints": [],
          "volumesFrom": [],
          "secrets": [
              {
                  "name": "SPRING_DATASOURCE_PASSWORD",
                  "valueFrom": "${var.ecs_api_taskdef_db_username_value_from}"
              },
              {
                  "name": "SPRING_DATASOURCE_USERNAME",
                  "valueFrom": "${var.ecs_api_taskdef_db_password_value_from}"
              }
          ],
          "ulimits": [],
          "logConfiguration": {
              "logDriver": "awslogs",
              "options": {
                  "awslogs-group": "/ecs/${var.ecs_api_taskdef_name}",
                  "mode": "non-blocking",
                  "awslogs-create-group": "true",
                  "max-buffer-size": "25m",
                  "awslogs-region": "${var.region}",
                  "awslogs-stream-prefix": "ecs"
              },
              "secretOptions": []
          },
          "systemControls": []
      }
  ]
TASKDEF

  runtime_platform {
    operating_system_family = "LINUX"
    cpu_architecture        = "X86_64"
  }
}
