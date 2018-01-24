package Levski

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{DataFrame, SQLContext}
import org.apache.spark.sql.types.{StructField, StructType}
import org.scalatest._
import org.scalatest.prop.Checkers
import org.apache.spark.sql.types._
import com.holdenkarau.spark.testing._
import org.scalacheck.Prop.forAll
import org.scalatest.prop.Checkers

class TestParser extends FlatSpec with SharedSparkContext with Checkers {

  import Levski.Parser._

  val testData = """2015-07-22T09:00:28.019143Z marketpalce-shop 123.242.248.130:54635 10.0.6.158:80 0.000022 0.026109 0.00002 200 200 0 699 "GET https://paytm.com:443/shop/authresponse?code=f2405b05-e2ee-4b0d-8f6a-9fed0fcfe2e0&state=null HTTP/1.1" "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.130 Safari/537.36" ECDHE-RSA-AES128-GCM-SHA256 TLSv1.2
2015-07-22T09:00:27.894580Z marketpalce-shop 203.91.211.44:51402 10.0.4.150:80 0.000024 0.15334 0.000026 200 200 0 1497 "GET https://paytm.com:443/shop/wallet/txnhistory?page_size=10&page_number=0&channel=web&version=2 HTTP/1.1" "Mozilla/5.0 (Windows NT 6.1; rv:39.0) Gecko/20100101 Firefox/39.0" ECDHE-RSA-AES128-GCM-SHA256 TLSv1.2
2015-07-22T09:00:27.885745Z marketpalce-shop 1.39.32.179:56419 10.0.4.244:80 0.000024 0.164958 0.000017 200 200 0 157 "GET https://paytm.com:443/shop/wallet/txnhistory?page_size=10&page_number=0&channel=web&version=2 HTTP/1.1" "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36" ECDHE-RSA-AES128-GCM-SHA256 TLSv1.2
2015-07-22T09:00:28.048369Z marketpalce-shop 180.179.213.94:48725 10.0.6.108:80 0.00002 0.002333 0.000021 200 200 0 35734 "GET https://paytm.com:443/shop/p/micromax-yu-yureka-moonstone-grey-MOBMICROMAX-YU-DUMM141CD60AF7C_34315 HTTP/1.0" "-" ECDHE-RSA-AES128-GCM-SHA256 TLSv1.2
2015-07-22T09:00:28.036251Z marketpalce-shop 120.59.192.208:13527 10.0.4.217:80 0.000024 0.015091 0.000016 200 200 68 640 "POST https://paytm.com:443/papi/v1/expresscart/verify HTTP/1.1" "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.89 Safari/537.36" ECDHE-RSA-AES128-GCM-SHA256 TLSv1.2
2015-07-22T09:00:28.033793Z marketpalce-shop 117.239.195.66:50524 10.0.6.195:80 0.000024 0.02157 0.000021 200 200 0 60 "GET https://paytm.com:443/api/user/favourite?channel=web&version=2 HTTP/1.1" "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36" ECDHE-RSA-AES128-GCM-SHA256 TLSv1.2
2015-07-22T09:00:28.055029Z marketpalce-shop 101.60.186.26:33177 10.0.4.244:80 0.00002 0.001098 0.000022 200 200 0 1150 "GET https://paytm.com:443/favicon.ico HTTP/1.1" "Mozilla/5.0 (Windows NT 6.3; rv:27.0) Gecko/20100101 Firefox/27.0" ECDHE-RSA-AES128-GCM-SHA256 TLSv1.2
2015-07-22T09:00:28.050298Z marketpalce-shop 59.183.41.47:62014 10.0.4.227:80 0.000021 0.008161 0.000021 200 200 0 72 "GET https://paytm.com:443/papi/rr/products/6937770/statistics?channel=web&version=2 HTTP/1.1" "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36" ECDHE-RSA-AES128-GCM-SHA256 TLSv1.2
2015-07-22T09:00:28.059081Z marketpalce-shop 117.239.195.66:50538 10.0.4.227:80 0.000019 0.001035 0.000021 200 200 0 396 "GET https://paytm.com:443/images/greyStar.png HTTP/1.1" "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36" ECDHE-RSA-AES128-GCM-SHA256 TLSv1.2
2015-07-22T09:00:28.054939Z marketpalce-shop 183.83.237.83:49687 10.0.6.108:80 0.000023 0.008762 0.000021 200 200 0 214 "GET https://paytm.com:443/shop/cart?channel=web&version=2 HTTP/1.1" "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.124 Safari/537.36" ECDHE-RSA-AES128-GCM-SHA256 TLSv1.2
2015-07-22T09:00:28.064841Z marketpalce-shop 117.195.91.36:25902 10.0.6.199:80 0.000019 0.002359 0.000022 200 200 0 16744 "GET https://paytm.com:443/offer?utm_source=Affiliates&utm_medium=OMG&utm_campaign=OMG&utm_term=762154_ HTTP/1.1" "Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko" ECDHE-RSA-AES128-SHA256 TLSv1.2
2015-07-22T09:00:28.064229Z marketpalce-shop 122.180.245.251:50203 10.0.6.108:80 0.000022 0.003904 0.000022 200 200 0 14169 "GET https://paytm.com:443/shop/summary/1116218422 HTTP/1.1" "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36" ECDHE-RSA-AES128-GCM-SHA256 TLSv1.2
2015-07-22T09:00:28.057527Z marketpalce-shop 117.198.215.20:53517 10.0.6.195:80 0.000022 0.011078 0.000015 200 200 0 8334 "GET https://paytm.com:443/blog/wp-content/themes/svbtle/style.css HTTP/1.1" "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.89 Safari/537.36" ECDHE-RSA-AES128-GCM-SHA256 TLSv1.2
2015-07-22T09:00:28.068481Z marketpalce-shop 223.176.154.91:51582 10.0.6.108:80 0.000022 0.001926 0.000021 302 302 0 534 "GET https://paytm.com:443/shop/login?isIframe=true&theme=mp-html5 HTTP/1.1" "Mozilla/5.0 (Linux; Android 4.2.2; GT-I9082 Build/JDQ39) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.135 Mobile Safari/537.36" ECDHE-RSA-AES128-GCM-SHA256 TLSv1.2
2015-07-22T09:00:28.002726Z marketpalce-shop 223.225.236.110:32279 10.0.4.176:80 0.000025 0.069531 0.000021 200 200 105 532 "POST https://paytm.com:443/api/v1/expresscart/checkout?wallet=1 HTTP/1.1" "Mozilla/5.0 (iPhone; CPU iPhone OS 6_1_2 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10B146 Safari/8536.25" ECDHE-RSA-AES128-SHA256 TLSv1.2
2015-07-22T09:00:28.064700Z marketpalce-shop 117.241.97.140:51029 10.0.6.178:80 0.000021 0.011796 0.000015 200 200 0 228 "GET https://paytm.com:443/shop/cart?channel=web&version=2 HTTP/1.1" "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36" ECDHE-RSA-AES128-GCM-SHA256 TLSv1.2
2015-07-22T09:00:28.059020Z marketpalce-shop 117.205.247.140:53039 10.0.4.244:80 0.000021 0.021522 0.000021 449 449 97 252 "POST https://paytm.com:443/papi/v1/expresscart/verify HTTP/1.1" "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36" ECDHE-RSA-AES128-GCM-SHA256 TLSv1.2
2015-07-22T09:00:28.071994Z marketpalce-shop 14.102.53.58:4252 10.0.6.108:80 0.000023 0.018186 0.000015 406 406 71 166 "POST https://paytm.com:443/shop/cart HTTP/1.1" "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.130 Safari/537.36" ECDHE-RSA-AES128-GCM-SHA256 TLSv1.2
2015-07-22T09:00:28.069908Z marketpalce-shop 203.200.99.67:41874 10.0.6.195:80 0.000021 0.020869 0.00002 200 200 0 822 "GET https://paytm.com:443/shop/cart?channel=web&version=2 HTTP/1.1" "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36" ECDHE-RSA-AES128-GCM-SHA256 TLSv1.2
2015-07-22T09:00:28.019497Z marketpalce-shop 107.167.109.204:38565 10.0.4.150:80 0.000023 0.072252 0.000021 302 302 189 74 "POST https://paytm.com:443/ HTTP/1.1" "Opera/9.80 (Windows Phone; Opera Mini/8.0.4/37.6066; U; en) Presto/2.12.423 Version/12.16" ECDHE-RSA-AES128-SHA TLSv1"""

  "Levski Parser function: generateKVPairs" should "return proper cnt" in {
    val sqlContext = new SQLContext(sc)
    import sqlContext.implicits._

    val result = generateKVPairs(sc.parallelize(testData.split("\n")))

    assert(result.count == 20)
  }

  "Levski Parser function: groupAndSortOnTs" should "return proper cnt and groupby the ip" in {
    val sqlContext = new SQLContext(sc)
    import sqlContext.implicits._

    val data = sc.parallelize(testData.split("\n"))

    val func = generateKVPairs andThen groupAndSortOnTs

    val result = func(data)

    assert(result.count == 19)
    assert(result.filter(_._1 == "117.239.195.66").count == 1)
  }



  "Levski Parser function: sessionizeData" should "return proper cnt and session value" in {
    val sqlContext = new SQLContext(sc)
    import sqlContext.implicits._

    val data = sc.parallelize(testData.split("\n"))

    val func = generateKVPairs andThen groupAndSortOnTs andThen sessionizeData

    val result = func(data)

    assert(result.count == 19)
    assert(result.filter(_._1 == "117.239.195.66").collect.head._2.flatten.head._1._1 == 26)
  }

  "Levski Parser function: commonStructure" should "return proper cnt, type and structure" in {
    val sqlContext = new SQLContext(sc)
    import sqlContext.implicits._

    val data = sc.parallelize(testData.split("\n"))

    val func = generateKVPairs andThen groupAndSortOnTs andThen sessionizeData andThen commonStructure

    val result = func(data)

    assert(result.count == 19)
    assert(result.filter(_._1 == "117.239.195.66").collect.head._2.flatten.size == 2)
  }

  "Levski Parser function: prepareForAnalytics" should "return proper cnt, type and structure" in {
    val sqlContext = new SQLContext(sc)
    import sqlContext.implicits._

    val data = sc.parallelize(testData.split("\n"))

    val result = prepareForAnalytics(data)

    assert(result.count == 19)
    assert(result.filter(_._1 == "117.239.195.66").collect.head._2.flatten.size == 2)
  }

  "Levski Parser function: averageSessionTime" should "return proper cnt, type and structure" in {
    val sqlContext = new SQLContext(sc)
    import sqlContext.implicits._

    val data = sc.parallelize(testData.split("\n"))

    val result = averageSessionTime(prepareForAnalytics(data))

    assert(result.count == 19)
    assert(result.filter(_._1 == "117.239.195.66").collect.head == ("117.239.195.66",26L))
  }

  "Levski Parser function: retrieveUniqUrl" should "return proper cnt, type and structure" in {
    val sqlContext = new SQLContext(sc)
    import sqlContext.implicits._

    val data = sc.parallelize(testData.split("\n"))

    val result = retrieveUniqUrl(prepareForAnalytics(data))

    assert(result.count == 19)
    assert(result.collect.filter(_._1 == "117.239.195.66").head == ("117.239.195.66", Set("https://paytm.com:443/api/user/favourite?channel=web&version=2", "https://paytm.com:443/images/greyStar.png")))
  }
}
