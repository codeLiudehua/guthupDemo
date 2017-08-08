package com.netease.uitls

import org.apache.spark.SparkContext

/**
 * Created by mengfanming on 2017/8/8.
 */
object BroadcastMap {
  //将数据装入全局map中
  def loadAllToBroadcastMap(dataPath:String, separator :String ,sc:SparkContext) : org.apache.spark.broadcast.Broadcast[scala.collection.Map[String,String]]={
    val result = sc.textFile(dataPath).map(line=>(line.split(separator)(0),line.split(separator)(1))).collectAsMap()
    val resultBroad = sc.broadcast(result)
    resultBroad

  }
  //统计数据放入到map中
  def labelCounterToBroadcastMap(dataPath:String, separator :String ,sc:SparkContext) : org.apache.spark.broadcast.Broadcast[scala.collection.Map[String,Int]]={
    val result = sc.textFile(dataPath).map(line=>(line.split(separator)(0),1)).reduceByKey(_ + _).map(f=>(f._1,f._2)).collectAsMap()
    val resultBroad = sc.broadcast(result)
    resultBroad

  }



}
