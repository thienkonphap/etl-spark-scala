package org.example

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.{col, lit}

class ViewClass(rawDF: DataFrame) {
  def fetchDataFrame() : DataFrame =  {
    rawDF.printSchema()
    // Select only BITCOIN columns
    val listCols= List("price","volume_24h", "change_1h", "change_24h","change_7d","total_supply",
                        "circulating_supply","market_cap","fully_diluted_market_cap", "updated_at")
    val finalDF  = rawDF.select(listCols.map(m=>col(m)):_*)

    // Add insert_date
    val update_date = finalDF.first().getString(9).substring(0, 10)
    finalDF.withColumn("insert_date", lit(update_date))

  }
}
