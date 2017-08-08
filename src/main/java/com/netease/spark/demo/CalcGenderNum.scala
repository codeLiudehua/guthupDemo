package com.netease.spark.demo

import com.netease.uitls.{DelHdfsPath, BroadcastMap, SparkUtils}
import org.apache.spark.SparkContext

/**
 * Created by mengfanming on 2017/8/8.
 */
object CalcGenderNum {
  def main(args: Array[String]) {
    //数据路径
    var dataPath :String = args(0)
    //存储数据路径
    var dataSavePath :String =args(1)


    val sc = SparkUtils.getSparkContext("calculate gender number")


    saveGenderNum(dataPath,dataSavePath," ",sc);
    val test = BroadcastMap.loadAllToBroadcastMap(dataSavePath,"\t",sc)
    //    test.value.foreach(f=>{println(f._1+"\t"+f._2)})


  }

  def saveGenderNum(dataPath:String , dataSavePath:String ,separator :String , sc : SparkContext): Unit ={
    val data = sc.textFile(dataPath)

    DelHdfsPath.delIfExit(dataSavePath)
    data.map(f=>{
      f.split(separator)(0)
    }).map((_,1)).reduceByKey(_+_).map(f=>{f._1+"\t"+f._2}).repartition(1).saveAsTextFile(dataSavePath)

  }





}
