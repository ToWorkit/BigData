package com.ml.spark_core.transformation

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.{Seconds, StreamingContext}

object ReduceByKey {

  val conf = new SparkConf().setAppName("Sample").setMaster("local[*]")
  val spark = SparkSession.builder().config(conf).getOrCreate()
  val sc = spark.sparkContext
  val ssc = new StreamingContext(sc, Seconds(2))

  def main(args: Array[String]): Unit = {
    reduceByKey()
    // shuffle 宽依赖
    groupByKey()
    CombinerByKey()
    AggregateByKey()
    FoldByKey()
  }

  /**
    * 根据Key进行聚合
    * 预聚合
    */
  def reduceByKey(): Unit = {
    val rdd = sc.parallelize(List(("female", 1), ("male", 5), ("female", 5), ("male", 2), ("female", 8)))
    // reduceByKey 聚合key
    // (_ + _) _ 参与聚合的 key 的 值 (_ + _) 累加
    val reduce = rdd.reduceByKey(_ + _)
    // List((female,14), (male,7))
    println(reduce.collect().toList)
  }

  /**
    * 将key相同的value聚集在一起。
    */
  def groupByKey(): Unit = {
    val rdd = sc.parallelize(List(("female", 1), ("male", 5), ("female", 5), ("male", 2), ("female", 8)))
    // 先分组
    val group = rdd.groupByKey()
    // List((female,CompactBuffer(1, 5, 8)), (male,CompactBuffer(5, 2)))
    println(group.collect().toList)

    // 再聚合
    val map = group.map(x => (x._1, x._2.sum))
    // List((female,14), (male,7))
    println(map.collect().toList)
  }

  /**
    * 会在shuffle之前(同一节点内)先做一次聚合操作
    */
  def CombinerByKey(): Unit = {
    val rdd = sc.parallelize(List(("female", 1), ("male", 5), ("female", 5), ("male", 2), ("female", 8)))
    val combine = rdd.combineByKey(
      (v) => (v, 1),
      (acc: (Int, Int), v) => (acc._1 + v, acc._2 + 1),
      (acc1: (Int, Int), acc2: (Int, Int)) => (acc1._1 + acc2._1, acc1._2 + acc2._2)
    )
    // List((female,(14,3)), (male,(7,2)))
    println(combine.collect().toList)
  }

  /**
    * def aggregateByKey[U: ClassTag](zeroValue: U, partitioner: Partitioner)(seqOp: (U, V) => U,combOp: (U, U) => U): RDD[(K, U)]
    * CombineByKey的简化版，可以通过zeroValue直接提供一个初始值。
    * 取各自个分区中 相同key 的最大值，然后再和其他分区相同key的值 进行求和
    */
  def AggregateByKey(): Unit = {
    val rdd = sc.parallelize(List((1, 3), (1, 2), (1, 4), (2, 3), (3, 6), (3, 8)), 3)
    val agg = rdd.aggregateByKey(0)(math.max(_, _), _ + _)
    // List((3,8), (1,7), (2,3))
    println(agg.collect().toList)

    def partitionsFun(index: Int, iter: Iterator[(Int, Int)]): Iterator[String] = {
      var lists = List[String]()
      while (iter.hasNext) {
        val next = iter.next()
        next match {
          // 查看每个分区都有什么
          case _ => lists = index + "->" + next :: lists
        }
      }
      lists.iterator
    }

    val result = rdd.mapPartitionsWithIndex(partitionsFun)
    // List(0->(1,2), 0->(1,3), 1->(2,3), 1->(1,4), 2->(3,8), 2->(3,6))
    println(result.collect().toList)
  }

  /**
    * aggregateByKey的简化操作
    * 相同key的值都求和
    */
  def FoldByKey(): Unit = {
    val rdd = sc.parallelize(List((1, 3), (1, 2), (1, 4), (2, 3), (3, 6), (3, 8)), 3)
    val agg = rdd.foldByKey(0)(_ + _)
    // List((3,14), (1,9), (2,3))
    println(agg.collect().toList)
  }
}
