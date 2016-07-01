package it.jesinity.sparkate

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by dgesino on 01/07/16.
  */
object PiEstimation {


  def main(args: Array[String]) {

    val conf: SparkConf = new SparkConf().setAppName("gigetto")
    conf.setMaster("local[16]")
    val sparkContext = new SparkContext(conf)

    val count = sparkContext.parallelize(1 to 10000).map{i =>
      val x = Math.random()
      val y = Math.random()
      if (x*x + y*y < 1) 1 else 0
    }.reduce(_ + _)
    println("Pi is roughly " + 4.0 * count / 10000)

  }

}
