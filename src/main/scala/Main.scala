package Levski

import org.apache.spark.sql.SparkSession
import java.io._
import Levski.Parser._

/**
  * bin/spark-shell --jars /Users/EdwinGuo/.ivy2/cache/joda-time/joda-time/jars/joda-time-2.9.9.jar,/Users/EdwinGuo/.ivy2/cache/org.typelevel/cats_2.11/jars/cats_2.11-0.9.0.jar,/Users/EdwinGuo/Levski/target/scala-2.11/delorean_2.11-0.1.0-SNAPSHOT.jar
  */
object Main extends Serializable {

  def main(args: Array[String]) {
    val spark = SparkSession
      .builder()
      .appName("Weblogchallenge")
    //.config()
      .getOrCreate()

    val filename = "file:////Users/EdwinGuo/WeblogChallenge/data/2015_07_22_mktplace_shop_web_log_sample.log"

    val data = spark.sparkContext.textFile(filename)

    val parsedData = prepareForAnalytics(data)

    parsedData.cache

    val sessionData = averageSessionTime(parsedData)

    val uniqUrlData = retrieveUniqUrl(parsedData)
  }
}
