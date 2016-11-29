package com.yhd.arch.tuna.test

import org.apache.spark.SparkContext
import org.apache.spark.streaming.dstream.DStream
import scala.collection.immutable.HashSet;
/**
  * Created by ww on 2016/9/20.
  */
trait Calculator {

  def test() : Unit = {
    val inputFile = "";
    val sc = new SparkContext("", "AdvancedSparkProgramming", System.getenv("SPARK_HOME"))
    val file = sc.textFile(inputFile)

    val callSigns = file.flatMap(line => {
      if (line == "") {
        //errorLines += 1
      } else {
        //dataLines +=1
      }
      line.split(" ")
    })

    val validSigns = callSigns.filter{sign => true
      //if ((callSignRegex findFirstIn sign).nonEmpty) {
        //validSignCount += 1; true
      //} else {
        //invalidSignCount += 1; false
      //}
    }

    validSigns.distinct().mapPartitions();
    val contactsContactLists = validSigns.distinct().mapPartitions{
      signs =>
//        val mapper = createMapper()
//        // create a connection pool
//        val client = new HttpClient()
//        client.start()
        // create http request
        signs.map {sign =>
          createExchangeForSign("client", sign)
          // fetch responses
        }
//          .map{ case (sign, exchange) =>
//          (sign, readExchangeCallLog(mapper, exchange))
//        }.filter(x => x._2 != null) // Remove empty CallLogs
    }

    def createExchangeForSign(client: String, sign: String): (String, String) = {
//      val exchange = new ContentExchange()
//      exchange.setURL(s"http://new73s.herokuapp.com/qsos/${sign}.json")
//      client.send(exchange)
      (sign, client)
    }
  }
}
