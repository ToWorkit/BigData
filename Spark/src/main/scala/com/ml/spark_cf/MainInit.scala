package com.ml.spark_cf

import org.apache.spark.sql.SparkSession

object MainInit {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[2]").enableHiveSupport().getOrCreate()
    val df_tmp_d = spark.sql("select user_id from tmp_d")

  }

}
