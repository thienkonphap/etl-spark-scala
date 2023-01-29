package org.example

import java.text.SimpleDateFormat
import java.util.Date

object Utility {
  def getApi(url: String): String={
    val result = (scala.io.Source.fromURL(url).mkString)
    result
  }
  def getCurrentDate(format: String): String={
    val dateFormatter = new SimpleDateFormat(format)
    val submittedDateConvert = new Date()
    dateFormatter.format(submittedDateConvert)
  }
}
