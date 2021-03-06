locals {
  table_name = "${terraform.workspace}_analytics_sip"
}

resource "aws_glue_catalog_table" "this" {
  name          = local.table_name
  database_name = var.database_name

  storage_descriptor {
    location      = var.location
    input_format  = "org.apache.hadoop.mapred.TextInputFormat"
    output_format = "org.apache.hadoop.hive.ql.io.HiveIgnoreKeyTextOutputFormat"

    ser_de_info {
      name                  = "json"
      serialization_library = "org.openx.data.jsonserde.JsonSerDe"
    }

    columns {
      name = "event"
      type = "struct<country:string,ipcToken:string,employmentStatus:string,isAbleToWorkFromHome:string,isLosingIncome:string,errors:string,agree:string,creationTime:string,uri:string>"
    }

    columns {
      name = "metadata"
      type = "struct<type:string,timestamp:string>"
    }
  }
}

resource "aws_athena_named_query" "sip_analytics_select_distinct_events_per_token" {
  name      = "${local.table_name}_select_distinct_events_per_token"
  database  = var.database_name
  workgroup = var.workgroup_name
  query     = <<EOF
SELECT event.ipcToken, metadata.type, count(metadata.type) 
FROM "${var.database_name}"."${local.table_name}" 
GROUP BY event.ipcToken, metadata.type
ORDER BY event.ipcToken
  EOF
}
