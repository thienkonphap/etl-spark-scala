package org.example
import org.apache.spark.sql.SparkSession
import CustomSchema.schema

/**
 * Hello world!
 *
 */
object MainDriver{
  def main(args: Array[String]): Unit ={
    //Initializing SparkSession
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

    val sparkHive = SparkSession.builder()
      .appName("Spark Session pour Hive")
      .config("hive.metastore.uris", "thrift://localhost:9883")
      .config("hive.exec.dynamic.partition","true")
      .config("hive.exec.dynamic.partition.mode", "nonstrict")
      .enableHiveSupport()
      .getOrCreate()

    finalDF.write
      .format("hive")
      .partitionBy("insert_date")
      .mode("append")
      .saveAsTable("price_bitcoin")
    println("End Programme")
  }
}
