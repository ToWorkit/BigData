package Location_demo

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 找到访问量最高的两个网页
  * 以本地模式运行： .setMaster("local")
  */

object WebCount {
  def main(args: Array[String]): Unit = {
    // 定义SparkContext对象
    val conf = new SparkConf().setAppName("WebCount").setMaster("local")

    // 创建SparkContext对象
    val sc = new SparkContext(conf)

    // 读入数据
    // 网站访问日志：192.168.88.1 - - [30/Jul/2017:12:53:43 +0800] "GET /MyDemoWeb/hadoop.jsp HTTP/1.1" 200 259
    val rdd1 = sc.textFile("E:\\local_data\\localhost_access_log.2017-07-30.txt").map(
      // 对读取到的每一条数据做处理
      line => {
        /**
          * 解析字符串，找到hadoop.jsp
          * 得到 .jsp 位置
          */
        // 得到第一个双引号的位置
        val index1 = line.indexOf("\"")
        // 得到最后一个双引号的位置
        val index2 = line.lastIndexOf("\"")

        /**
          * 得到子串 GET /MyDemoWeb/hadoop.jsp HTTP/1.1
         */
        val line1 = line.substring(index1 + 1, index2)
        // 子串的第一个空格
        val index3 = line1.indexOf(" ")
        // 子串的最后一个空格
        val index4 = line1.lastIndexOf(" ")

        /**
          * 得到子串 /MyDemoWeb/hadoop.jsp
          */
        val line2 = line1.substring(index3 + 1, index4)

        /**
          * 得到 hadoop.jsp
          */
        val jspPage = line2.substring(line2.lastIndexOf("/") + 1)

        // 返回的格式
        (jspPage, 1)
      }
    )

    // 按照 key(jspPage) 进行聚合操作
    val rdd2 = rdd1.reduceByKey(_ + _)

    // 按照 value排序(降序) 数据 (hadoop.jsp, 10)
    val rdd3 = rdd2.sortBy(_._2, false)

    // 取出排名最高的两个网页
    // 转为变长数组形式
    println(rdd3.take(2).toBuffer) // ArrayBuffer((oracle.jsp,9), (hadoop.jsp,9))

    // 关闭停止
    sc.stop()
  }
}
