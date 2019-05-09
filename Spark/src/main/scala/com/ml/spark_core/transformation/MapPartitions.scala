package com.ml.spark_core.transformation

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.{Seconds, StreamingContext}

object MapPartitions {

  val conf = new SparkConf().setAppName("Map").setMaster("local[*]")
  val spark = SparkSession.builder().config(conf).getOrCreate()
  val sc = spark.sparkContext
  val ssc = new StreamingContext(sc, Seconds(2))

  val rdd = sc.makeRDD(List(("a", "1"), ("b", "2"), ("c", "3"), ("d", "1"), ("a", "5")), 4)

  def main(args: Array[String]): Unit = {
    // 打印 分区数
    // 4
    println(rdd.partitions.size)

    MapPartitions()
    MapPartitionsWithIndex()
  }

  /**
    * 类似于map，但是独立的在RDD的每一个分区上运行
    *   在类型为T的RDD上运行时，函数方法的类型必须是 Iterator[T] => Iterator[U]
    *   假设N个元素有M的分区，那么map函数会被调用N次，mapPartitions被调用M次
    *   rdd有10元素，3个分区，map会调用10次，mapPartitions只调用3次
    *   mapPartition可以倒过来理解，先partition，再把每个partition进行map函数，
    *     如果在映射的过程中需要频繁创建额外的对象，使用mapPartitions要比map高效的多。
    *     比如，将RDD中的所有数据通过JDBC连接写入数据库，如果使用map函数，可能要为每一个元素都创建一个connection，这样开销很大
    *     如果使用mapPartitions，那么只需要针对每一个分区建立一个connection
    */
  def MapPartitions(): Unit = {
      def partitionsFun(iter: Iterator[(String, String)]): Iterator[String] = {
        var lists = List[String]()
        while (iter.hasNext) {
          val next = iter.next()

          /**
            * (c,3)
            * (d,4)
            * (a,5)
            * (a,1)
            * (b,2)
            */
          println(next)
          // 模式匹配
          next match {
            // :: 向队列的头部追加数据，创造新的列表
            // 左边可以是任意值
            // ::: 这个方法只能用于两个List类型的集合
            // next._1 取第一个
            // (第一个值任意，第二个值为 "1")
            case (_, "1") => lists = next._1 :: lists
            case _ => None
          }
        }
        lists.iterator
      }

    val result = rdd.mapPartitions(partitionsFun)

    // List(a, d)
    println(result.collect().toList)
  }

  /**
    * 与mapPartitionWithIndex类似，也是按照分区进行的map操作，不过mapPartitionsWithIndex传入的参数多了一个分区的值
    */
  def MapPartitionsWithIndex(): Unit = {
    def partitionsFun(index: Int, iter: Iterator[(String, String)]): Iterator[String] = {
      var lists = List[String]()
      while (iter.hasNext) {
        val next = iter.next()
        next match {
//          case (_, "1") => lists = index + "->" + next._1 :: lists
          // 查看每个分区都有什么
          case _ => lists = index + "->" + next :: lists
        }
      }
      lists.iterator
    }

    val result = rdd.mapPartitionsWithIndex(partitionsFun)
    // List(0->a, 3->d)
    // List(0->(a,1), 1->(b,2), 2->(c,3), 3->(a,5), 3->(d,1))
    println(result.collect().toList)
  }
}
