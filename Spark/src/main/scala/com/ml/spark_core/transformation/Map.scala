package com.ml.spark_core.transformation

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Map {

  val conf = new SparkConf().setAppName("Map").setMaster("local[*]")
  val spark = SparkSession.builder().config(conf).getOrCreate()
  val sc = spark.sparkContext
  val ssc = new StreamingContext(sc, Seconds(2))

  def main(args: Array[String]): Unit = {
    /*  val spark = SparkSession.builder().master("local[*]").appName("Map").getOrCreate()
  val sc = spark.sparkContext
  val ssc = new StreamingContext(sc, Seconds(2))*/

    // 创建RDD
    // 1.makeRDD
    // 2.parallelize
    // 3.文件读取
    val intRdd = sc.parallelize(1 to 10)

    // rdd.collect() => Array[Int]形式， Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    // rdd.collect().toList => 数组转为列表，List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(intRdd.collect().toList)

    // strRdd
    val strRdd = sc.makeRDD(Array("abc", "abd", "dbc", "dba", "cba", "aba"))

    // 窄依赖
    Map(intRdd)
    Filter(strRdd)
    // 多到一
    FlatMap(intRdd)
    Som()
  }

  /**
    * 返回一个新的RDD，该RDD由 每一个输入元素经过函数方法处理之后 组成
    */
  def Map(intRdd: RDD[Int]): Unit = {
    /**
      * 注意有些地方
      *   (x => x * 2)
      * 不能简写为
      *   (_ * 2)
      * 因为
      *   Scala 会将其解析为 ((y => x * 2)) x 为 undefined
      */
    val mapRdd = intRdd.map(_ * 2)
    // List(2, 4, 6, 8, 10, 12, 14, 16, 18, 20)
    println(mapRdd.collect().toList)

    // intRdd.map((_, 1)) 相当于 intRdd.map(x => (x, 1))
    val mapRdd_02 = intRdd.map((_, 1))
    // List((1,1), (2,1), (3,1), (4,1), (5,1), (6,1), (7,1), (8,1), (9,1), (10,1))
    println(mapRdd_02.collect().toList)
  }

  /**
    * 返回一个新的RDD，由 函数方法返回值为true的值 组成
    */
  def Filter(strRDD:  RDD[String]): Unit = {
    val filterRdd = strRDD.filter(_.contains("ab"))
    // List(abc, abd, aba)
    println(filterRdd.collect().toList)

    /**
      * dba
      * abd
      * dbc
      */
    for (item <- strRDD if item.contains("d")) println(item)
  }

  /**
    * 压平操作，将多个RDD集合合并为一个
    */
  def FlatMap(intRDD: RDD[Int]): Unit = {
    /**
      * Array("I", "Love", "You")
      * Array("You", "Love", "Me")
      */
    // Array("I", "Love", "You", "You", "Love", "Me")

    // x => (1 to x) 每一项都会生成一个RDD集合 RDD[Int]
    // 最后合并为 一个集合
    val flatMapRdd = intRDD.flatMap(x => (1 to x))
    // List(1, 1, 2, 1, 2, 3, 1, 2, 3, 4, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(flatMapRdd.collect().toList)
  }

  /**
    * 整合
    */
  def Som(): Unit = {
    val nums = List(1, 2, 3, 4, 5, 6, 7, 8)

    // foreach 对列表中的每一个元素应用一个函数，没有返回值
    println(nums.foreach((i: Int) => i * 2)) // ()
    nums.foreach(print) // 12345678

    // map 对列表中的每个元素应用一个函数并返回
    println(nums.map(_ * 2)) // List(2, 4, 6, 8, 10, 12, 14, 16)

    // flatten 把嵌套的结构展开
    println(List(List(1, 2, 3), List(4, 5, 6)).flatten) // List(1, 2, 3, 4, 5, 6)

    // flatMap combinator结合了map和flatten的功能
    println(List(List(1, 2, 3), List(4, 5, 6)).flatMap(x => x.map(_ * 2))) // List(2, 4, 6, 8, 10, 12)
    println(List(List(1, 2, 3), List(4, 5, 6)).flatMap(_.map(_ * 2))) // List(2, 4, 6, 8, 10, 12)
  }
}
