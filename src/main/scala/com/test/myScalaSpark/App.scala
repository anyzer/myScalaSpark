package com.test.myScalaSpark

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

import scala.collection.JavaConverters._
import java.util.ArrayList
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.ObjectListing
import com.amazonaws.services.s3.model.S3ObjectSummary
import com.amazonaws.services.s3.model.ListObjectsRequest

/**
 * @author ${user.name}
 */
object App {

  def main(args : Array[String]) {
    val conf = new SparkConf()
      .setAppName("The swankiest Spark app ever")
      .setMaster("local")

    val sc = new SparkContext(conf)

    val col = sc.parallelize(0 to 100 by 5)
    val smp = col.sample(true, 4)
    val colCount = col.count
    val smpCount = smp.count
    
    println("orig count = " + colCount)
    println("sampled count = " + smpCount)
    
//    val test = Array("A", "B", "B", "C", "C", "C")
    val test = sc.textFile("./food.txt")
    
    test.map(line => line.length).reduce((a, b) => a + b)
    
    println("Running Finished")
    sc.stop
  }

}


//  def main(args: Array[String]) = {
//    
//    val conf = new SparkConf().setAppName("WordCount").setMaster("local")
//    val sc = new SparkContext(conf)
//
//    val test = sc.textFile("food.txt")
//    test.flatMap { line => 
//      line.split(" ")
//      }
//      .map { word => 
//        (word, 1)  
//      }
//      .reduceByKey(_ + _)
//      .saveAsTextFile("food.count.txt")
//
//      sc.stop
//  }