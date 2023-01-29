package org.example
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.types.{StringType, StructType}
import CustomSchema.schema

import scala.util.parsing.json._

/**
 * Hello world!
 *
 */
object MainDriver{
  def main(args: Array[String]): Unit ={
    //Initial SparkSession
    val spark: SparkSession = SparkSession.builder()
      .appName("Test application")
      .master("local")
      .getOrCreate()
    val sqlContext = spark.sqlContext

    // Get API
    val url = "https://cryptingup.com/api/assets/BTC"
    var result = Utility.getApi(url)
    result = result.substring(9,result.length-1)
    println(result)

    //Transform jsonString => DataFrame
    val vals = spark.sparkContext.parallelize(
      result :: Nil)
    val jsonDF = sqlContext.read.schema(schema).json(vals)

    // write Raw Data Frame to HDFS
    val currentDate = Utility.getCurrentDate("yyyyMMdd")
    val pathHDFS = "hdfs://localhost:9000/user/nguyenngocthien/user/dev/raw/"
    jsonDF.write.mode("append").json(pathHDFS+ s"/${currentDate}")

    //Transform raw DataFrame

    val finalDF = new ViewClass(jsonDF).fetchDataFrame()
    finalDF.show()

    // Save transformed Dataframe to HDFS
    finalDF.write.mode("append")
      .format("parquet")
      .partitionBy("insert_date")
      .save("hdfs://localhost:9000/user/nguyenngocthien/user/dev/srv/")
    val df = spark.read.schema(schema).json("hdfs://localhost:9000/user/nguyenngocthien/user/dev/raw/20230129")
    df.show()
    println("End Programme")
  }
}
