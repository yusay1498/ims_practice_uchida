# https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/rds_cluster
resource "aws_rds_cluster" "main" {
  cluster_identifier = var.db_cluster_id
  engine             = "aurora-postgresql"
  # When using serverless-v2, set ‘provisioned’.
  engine_mode        = "provisioned"
  engine_version     = var.db_engine_version
  master_username    = var.db_username
  master_password    = var.db_password
  database_name      = var.db_name
  storage_encrypted  = true

  serverlessv2_scaling_configuration {
    max_capacity = 1
    min_capacity = 0.5
  }
}

# https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/rds_cluster_instance
resource "aws_rds_cluster_instance" "main_node1" {
  cluster_identifier = aws_rds_cluster.main.id
  engine             = aws_rds_cluster.main.engine
  engine_version     = aws_rds_cluster.main.engine_version
  instance_class     = "db.serverless"
}

resource "aws_rds_cluster_instance" "main_node2" {
  cluster_identifier = aws_rds_cluster.main.id
  engine             = aws_rds_cluster.main.engine
  engine_version     = aws_rds_cluster.main.engine_version
  instance_class     = "db.serverless"
}
