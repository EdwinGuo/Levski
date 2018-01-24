package Levski

import org.joda.time.format._
import org.apache.spark.rdd.RDD
import java.io.Serializable

object Parser extends Serializable {
  // 15 minutes session window
  val sessionTime = 900000

  @transient
  val fmt = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ")

  type KVPairsTyp = RDD[(String, List[(Long, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String)])]

  type MsgListTyp = List[((Long, (Long, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String)), Int)]

  type MsgListOutputTyp = List[List[((Long, (Long, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String)), Int)]]

  type SessionizeTyp = RDD[(String, MsgListOutputTyp)]

  type AnalyticsTyp = RDD[(String, List[List[(Long, String)]])]

  /**
    * massage the row to come up with key value pair (key is request ip, value
    * contains all the fields)
    */
  def generateKVPairs: PartialFunction[RDD[String], KVPairsTyp] = {
    case input: RDD[String] => input.map(data => {
      val row = data.split(" \"").flatMap(_.split("\" ")).filter(f => f != " ").toList;
      row(0).split(" ") ++ List(row(1)) ++ List(row(2)) ++ row(3).split(" ")})
        .map(row => (row(2).split(":")(0), List((fmt.parseMillis(row(0)), row(0), row(1), row(2), row(3), row(4), row(5), row(6), row(7), row(8), row(9), row(10), row(11), row(12), row(13), row(14)))))
  }

  /**
    * group ip and sort base on timestamp
    */
  def groupAndSortOnTs: PartialFunction[KVPairsTyp, KVPairsTyp] = {
    case input: KVPairsTyp => input.reduceByKey(_ ++ _).map(f => (f._1, f._2.sortBy(_._1)))
  }

  /**
    * to group the row into each session busket
    */
  def massageList(lst: MsgListTyp, cond: List[Int]): MsgListOutputTyp ={
    if (cond.isEmpty)
    {List(lst)}
    else
    {List(lst.take(cond.head)) ++ massageList(lst.drop(cond.head), cond.tail.map(_ - cond.head))}
  }

  /**
    * put row into each session
    */
  def sessionizeData: PartialFunction[KVPairsTyp, SessionizeTyp] = {
    case input: KVPairsTyp => input.map(f => (f._1, {val ts_pre = f._2.map(_._1);
      val ts = ts_pre.tail.padTo(ts_pre.size, ts_pre.last);
      f._2.zip(ts).map(t => (t._2 - t._1._1, t._1)).zipWithIndex})).map(d => {val break_point = d._2.filter(_._1._1 > sessionTime).map(_._2); (d._1, massageList(d._2, break_point))})
  }

  /**
    * Prepare a common data structure for future analytics
    */
  def commonStructure: PartialFunction[SessionizeTyp, AnalyticsTyp] = {
    case input: SessionizeTyp => input.map(f => (f._1, f._2.map(_.map(d => (d._1._2._1, d._1._2._13)))))
  }

  /**
    * Chain functions together
    */
  def prepareForAnalytics: PartialFunction[RDD[String], AnalyticsTyp] = {
    generateKVPairs andThen
    groupAndSortOnTs andThen
    sessionizeData andThen
    commonStructure
  }

  /**
    * average session time
    */
  def averageSessionTime(input: AnalyticsTyp): RDD[(String, Long)] = {
    input.flatMap(f => f._2.map(g => (f._1, g)))
      .filter(!_._2.isEmpty)
      .map(f => (f._1, f._2.last._1 - f._2.head._1))
  }

  /**
    * unique url per session
    */
  def retrieveUniqUrl(input: AnalyticsTyp) = {
    input.flatMap(f => f._2.map(g => (f._1, g))).filter(!_._2.isEmpty).map(f => (f._1, f._2.map(_._2.split(" ")(1)).toSet))
  }
}
