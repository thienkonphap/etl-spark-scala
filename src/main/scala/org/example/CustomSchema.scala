package org.example
import org.apache.spark.sql.types.{StringType, StructType,DoubleType}

object CustomSchema {
  val schema = (new StructType)
      .add("quote", (new StructType)
        .add("CAD", (new StructType)
          .add("price", DoubleType)
          .add("volumn_24h", DoubleType)
          .add("market_cap", DoubleType)
          .add("fully_diluted_market_cap", DoubleType)
        )
        .add("AUD", (new StructType)
          .add("price", DoubleType)
          .add("volumn_24h", DoubleType)
          .add("market_cap", DoubleType)
          .add("fully_diluted_market_cap", DoubleType)
        )
        .add("NZD", (new StructType)
          .add("price", DoubleType)
          .add("volumn_24h", DoubleType)
          .add("market_cap", DoubleType)
          .add("fully_diluted_market_cap", DoubleType)
        )
        .add("EUR", (new StructType)
          .add("price", DoubleType)
          .add("volumn_24h", DoubleType)
          .add("market_cap", DoubleType)
          .add("fully_diluted_market_cap", DoubleType)
        )
        .add("JPY", (new StructType)
          .add("price", DoubleType)
          .add("volumn_24h", DoubleType)
          .add("market_cap", DoubleType)
          .add("fully_diluted_market_cap", DoubleType)
        )
        .add("GBP", (new StructType)
          .add("price", DoubleType)
          .add("volumn_24h", DoubleType)
          .add("market_cap", DoubleType)
          .add("fully_diluted_market_cap", DoubleType)
        )
    )
    .add("asset_id", StringType)
    .add("name", StringType)
    .add("description", StringType)
    .add("website", StringType)
    .add("ethereum_contract_address", StringType)
    .add("pegged", StringType)
    .add("price", DoubleType)
    .add("volume_24h", DoubleType)
    .add("change_1h", DoubleType)
    .add("change_24h", DoubleType)
    .add("change_7d", DoubleType)
    .add("total_supply", DoubleType)
    .add("circulating_supply", DoubleType)
    .add("max_supply", DoubleType)
    .add("market_cap", DoubleType)
    .add("fully_diluted_market_cap", DoubleType)
    .add("status", StringType)
    .add("created_at", StringType)
    .add("updated_at", StringType)
}
