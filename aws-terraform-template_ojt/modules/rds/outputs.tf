output "db_writer_endpoint" {
  value = aws_rds_cluster.main.endpoint
}

output "db_reader_endpoint" {
  value = aws_rds_cluster.main.reader_endpoint
}
