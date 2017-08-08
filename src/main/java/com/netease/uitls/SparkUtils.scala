package com.netease.uitls

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{Path, FileSystem}
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Created by mengfanming on 2017/8/8.
 */
object SparkUtils {
  def getLocalSparkContext(appName: String): SparkContext = {
    System.setProperty("hadoop.home.dir", "D:\\programFiles\\hadoop")
    new SparkContext(new SparkConf().setAppName(appName).setMaster("local"))
  }

  def getSparkContext(appName: String): SparkContext = {
    new SparkContext(new SparkConf().setAppName(appName).set("spark.shuffle.blockTransferService", "nio"))
  }

  def dirClean(arr: Array[String]): Unit = {
    val hdfs = FileSystem.get(new Configuration())
    arr.foreach(p => {
      val pPath = new Path(p)
      if (hdfs.exists(pPath)) {
        hdfs.delete(pPath, true)
      }
    })
  }
}
