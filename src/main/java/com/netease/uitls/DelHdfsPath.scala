package com.netease.uitls

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{Path, FileSystem}

/**
 * Created by mengfanming on 2017/8/8.
 */
object DelHdfsPath {

  def delIfExit(p : String): Unit ={
    val hdfs = FileSystem.get(new Configuration())
    val pPath = new Path(p)
    if(hdfs.exists(pPath)){
      hdfs.delete(pPath,true)
    }
  }

}