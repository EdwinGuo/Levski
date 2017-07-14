import org.joda.time.format._

val s1 = spark.sparkContext.textFile("file:////Users/EdwinGuo/WeblogChallenge/data/2015_07_22_mktplace_shop_web_log_sample.log")

//val format = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
val format = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ")

// massage the row to come up with key value pair (key is request ip, value contains all the fields)
val s2 = s1.map(data => {
  val row = data.split(" \"").flatMap(_.split("\" ")).filter(f => f != " ").toList;
  row(0).split(" ") ++ List(row(1)) ++ List(row(2)) ++ row(3).split(" ")})
  .map(row => (row(2).split(":")(0), List((format.parse(row(0)).getTime, row(0), row(1), row(2), row(3), row(4), row(5), row(6), row(7), row(8), row(9), row(10), row(11), row(12), row(13), row(14)))))

// group ip and sort base on timestamp
val s3 = s2.reduceByKey(_ ++ _).map(f => (f._1, f._2.sortBy(_._1)))

// to group the row into each session busket
def massageList(lst: List[((Long, (Long, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String)), Int)], cond: List[Int]): List[List[((Long, (Long, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String)), Int)]] =
{if (cond.isEmpty)
{List(lst)}
else
{List(lst.take(cond.head)) ++ massageList(lst.drop(cond.head), cond.tail.map(_ - cond.head))}}

// 15 minutes session window
val sessionTime = 900000
//  put row into each session busket
val s4 = s3.map(f => (f._1, {val ts_pre = f._2.map(_._1);
  val ts = ts_pre.tail.padTo(ts_pre.size, ts_pre.last);
  f._2.zip(ts).map(t => (t._2 - t._1._1, t._1)).zipWithIndex})).map(d => {val break_point = d._2.filter(_._1._1 > sessionTime).map(_._2); (d._1, massageList(d._2, break_point))})

//get a data structure for analysis
val s5 = s4.map(f => (f._1, f._2.map(_.map(d => (d._1._2._1, d._1._2._13)))))

//average session time
val averageSessionTime = s5.flatMap(f => f._2.map(g => (f._1, g))).filter(!_._2.isEmpty).map(f => (f._1, f._2.last._1 - f._2.head._1))

// unique url per session
val uniqUrl = s5.flatMap(f => f._2.map(g => (f._1, g))).filter(!_._2.isEmpty).map(f => (f._1, f._2.map(_._2.split(" ")(1)).toSet))

