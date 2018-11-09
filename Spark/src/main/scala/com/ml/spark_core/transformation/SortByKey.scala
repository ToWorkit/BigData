package com.ml.spark_core.transformation

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.{Seconds, StreamingContext}

object SortByKey {
  val conf = new SparkConf().setAppName("Sample").setMaster("local[*]")
  val spark = SparkSession.builder().config(conf).getOrCreate()
  val sc = spark.sparkContext
  val ssc = new StreamingContext(sc, Seconds(2))

  def main(args: Array[String]): Unit = {
    SortByKey()
    SortBy()
    Join()
    Cogroup()
    Cartesian()
  }

  /**
    * 根据Key来进行排序，如果 Key 不支持排序，需要with Ordering接口，实现compare方法，告诉spark key 的大小如何判定
    */
  def SortByKey(): Unit = {
    val rdd = sc.parallelize(Array((3, "aa"), (6, "cc"), (2, "bb"), (1, "dd")))
    // 升序
    val sortRdd = rdd.sortByKey(true)
    // List((1,dd), (2,bb), (3,aa), (6,cc))
    println(sortRdd.collect().toList)

    val sortRddF = rdd.sortByKey(false)
    // List((6,cc), (3,aa), (2,bb), (1,dd))
    println(sortRddF.collect().toList)
  }

  /**
    * 根据函数方法提供可以排序的 key
    */
  def SortBy(): Unit = {
    val rdd = sc.parallelize(List(1, 2, 3, 4))
    val rdd1 = rdd.sortBy(x => x)
    // List(1, 2, 3, 4)
    println(rdd1.collect().toList)

    val rdd2 = rdd.sortBy(x => x % 3)
    // List(3, 1, 4, 2)
    println(rdd2.collect().toList)
  }

  /**
    * 连接两个RDD
    * JOIN    只留下双方都有的 key
    * LEFT JOIN   留下左边RDD所有的数据
    * RIGHT JOIN  留下右边RDD所有的数据
    */
  def Join(): Unit = {
    val rdd = sc.parallelize(Array((1, "a"), (2, "b"), (3, "c")))
    val rdd1 = sc.parallelize(Array((1, 4), (2, 5), (3, 6)))

    val rddJoin = rdd.join(rdd1)
    // List((1,(a,4)), (2,(b,5)), (3,(c,6)))
    println(rddJoin.collect().toList)
  }

  /**
    * 将相同key的数据聚集在一起
    */
  def Cogroup(): Unit = {
    val rdd = sc.parallelize(Array((1, "a"), (2, "b"), (3, "c")))
    val rdd1 = sc.parallelize(Array((1, 4), (2, 5), (3, 6)))
    val rddCog = rdd.cogroup(rdd1)
    // List((1,(CompactBuffer(a),CompactBuffer(4))), (2,(CompactBuffer(b),CompactBuffer(5))), (3,(CompactBuffer(c),CompactBuffer(6))))
    println(rddCog.collect().toList)

    val rdd2 = sc.parallelize(Array((4,4),(2,5),(3,6)))
    val rddCog_1 = rdd.cogroup(rdd2)
    // List((4,(CompactBuffer(),CompactBuffer(4))), (1,(CompactBuffer(a),CompactBuffer())), (2,(CompactBuffer(b),CompactBuffer(5))), (3,(CompactBuffer(c),CompactBuffer(6))))
    println(rddCog_1.collect().toList)

    val rdd3 = sc.parallelize(Array((1,"a"),(1,"d"),(2,"b"),(3,"c")))
    val rddCog_2 = rdd3.cogroup(rdd2)
    // List((4,(CompactBuffer(),CompactBuffer(4))), (1,(CompactBuffer(a, d),CompactBuffer())), (2,(CompactBuffer(b),CompactBuffer(5))), (3,(CompactBuffer(c),CompactBuffer(6))))
    println(rddCog_2.collect().toList)
  }

  /**
    * 笛卡尔积
    * n * m
    */
  def Cartesian(): Unit = {
    // 1 2 3
    val rdd1 = sc.parallelize(1 to 3)
    // 2 3 4 5
    val rdd2 = sc.parallelize(2 to 5)
    val rddCartesian = rdd1.cartesian(rdd2)
    // List((1,2), (1,3), (1,4), (1,5), (2,2), (2,3), (2,4), (2,5), (3,2), (3,3), (3,4), (3,5))
    println(rddCartesian.collect().toList)
  }

  /**
    * 执行外部脚本
    */
  def Pipe(): Unit = {
    val rdd = sc.parallelize(List("hi","Hello","how","are","you"),1)
    val rdd1 = rdd.pipe("/python/shell/pipe.sh")
  }
}
