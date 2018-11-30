package com.ml.spark_core.action

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Reduce {

  val conf = new SparkConf().setAppName("Map").setMaster("local[*]")
  val spark = SparkSession.builder().config(conf).getOrCreate()
  val sc = spark.sparkContext
  val ssc = new StreamingContext(sc, Seconds(2))

  def main(args: Array[String]): Unit = {
    Reduce()
    Collect()
    Aggregate()
    Fold()
    //    SaveAsTextFile()
    CountByKey()
    Foreach()
    ForeachPartition()
  }

  /**
    * 聚合
    */
  def Reduce(): Unit = {
    val rdd = sc.makeRDD(1 to 10, 2)
    // 55
    println(rdd.reduce(_ + _))

    val rdd2 = sc.makeRDD(Array(("a", 1), ("a", 3), ("c", 3), ("d", 5)))
    // (adac,12)
    println(rdd2.reduce((x, y) => (x._1 + y._1, x._2 + y._2)))
  }

  /**
    * 以数组的形式返回数据集的所有元素
    */
  def Collect(): Unit = {
    val rdd = sc.makeRDD(1 to 10, 2)
    // class [I
    println(rdd.collect().getClass)

    /**
      * 返回RDD的元素个数
      */
    // 10
    println(rdd.count())

    /**
      * 返回RDD的第一个元素（类似于take(1)）
      */
    // 1
    println(rdd.first())

    /**
      * 返回一个由数据集的前n个元素组成的数组
      */
    // List(1, 2)
    println(rdd.take(2).toList)

    /**
      * 采样
      * 返回一个数组，该数组由从数据集中随机采样的num个元素组成
      */
    // List(1, 1, 10)
    println(rdd.takeSample(true, 3).toList)

    /**
      * 数组，返回排序后的前几个数据
      */
    // List(1, 2, 3)
    println(rdd.takeOrdered(3).toList)
  }

  /**
    * 各自个分区 内先求和，然后再进行求和
    * * @param seqOp an operator used to accumulate results within a partition
    * * @param combOp an associative operator used to combine results from different partitions
    */
  def Aggregate(): Unit = {
    val rdd = sc.makeRDD(1 to 10, 3)
    // 初始值为 0
    val result = rdd.aggregate(0)(
      { (x: Int, y: Int) => x + y }, // 各自分区内求和
      { (a: Int, b: Int) => a + b } // 然后再求和
    )
    // 55
    println(result)
  }

  /**
    * aggregate的简化操作，seqOp和combOp相同
    */
  def Fold(): Unit = {
    var rdd = sc.makeRDD(1 to 4, 2)
    val result = rdd.fold(0)(_ + _)
    // 10
    println(result)
  }

  /**
    * 将数据集的元素以 textfile 的形式保存到HDFS上或者其他地方
    * saveAsSequenceFile 将文件存储为SequenceFile
    * saveAsObjectFile  用于将RDD中的元素序列化成对象，存储到文件中, 将文件存储为ObjectFile
    */
  def SaveAsTextFile(): Unit = {
    var rdd = sc.parallelize(1 to 4, 2)
    rdd.saveAsTextFile("hdfs://bigdata11:9000/rdd")
  }

  /**
    * Map集合, 返回 key 的个数
    */
  def CountByKey(): Unit = {
    val rdd = sc.parallelize(List((1, 3), (1, 2), (1, 4), (2, 3), (3, 6), (3, 8)), 3)
    // Map(3 -> 2, 1 -> 3, 2 -> 1)
    println(rdd.countByKey())
  }

  /**
    * 对每一个元素进行处理
    */
  def Foreach(): Unit = {
    var rdd = sc.makeRDD(1 to 10,2)
    // 累加器
    var sum = sc.accumulator(0)
    rdd.foreach(sum += _)

    // 55
    println(sum.value)
  }

  /**
    * 对分区内的Rdd操作
    *   没有返回值并且是action操作
    *   在程序末尾比如说要落地数据到存储系统中如mysql，es，或者hbase中，可以用它
    */
  def ForeachPartition(): Unit = {
    var rdd = sc.makeRDD(1 to 10,2)
    rdd.foreachPartition(partition => {
      /**
        * 5
        * 5
        */
//      println(partition.size) 执行size操作之后下面会没有数据，因为没有返回值
      partition.foreach(item => {
        /**
          * 6
          * 7
          * 8
          * 9
          * 10
          * 1
          * 2
          * 3
          * 4
          * 5
          */
        println(item)
      })
    })
  }
}
