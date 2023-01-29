package org.example
import org.apache.spark.SparkException

/**
 * Hello world!
 *
 */
object App extends App {
  println( "Hello World!" )

  override def main(args: Array[String]): Unit =
    {
      val a = Utility.getCurrentDate("yyyyMMdd")
      println(a)
    }
}
