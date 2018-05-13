package Scala_Server;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class JavaWordCount {
    public static void main(String[] args) {
        // 创建一个Config对象，配置参数
        SparkConf conf = new SparkConf().setAppName("MyJavaWordCount");

        // 创建一个SparkContext对象
        JavaSparkContext context = new JavaSparkContext(conf);

        // 读入数据
        JavaRDD<String> lines = context.textFile(args[0]);

        /**
         * 分词
         * FlatMapFunction<String, U>
         *      String 读入的每一句
         *      U 返回值
         */
        JavaRDD<String> words = lines.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterator<String> call(String line) throws Exception {
                // 数据 I Love You
                // 分词
                return Arrays.asList(line.split(" ")).iterator();
            }
        });

        /**
         * 每个单词记一次数
         *  PairFunction<String, k2, v2>
         *      String -> 每个单词
         *      k2, v2 -> 相当于是Map的输出
         */
        JavaPairRDD<String, Integer> wordOne = words.mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String word) throws Exception {
                // Love -> (Love, 1)
                return new Tuple2<String, Integer>(word, 1);
            }
        });

        // 执行Reduce计算
        JavaPairRDD<String, Integer> count = wordOne.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer a, Integer b) throws Exception {
                return a + b;
            }
        });

        // 执行计算，执行action: 可以把结果直接打印在屏幕上
        List<Tuple2<String, Integer>> result = count.collect();

        // 输出到屏幕
        for (Tuple2<String, Integer> tuple: result) {
            System.out.println(tuple._1 + "\t" + tuple._2);
        }

        // 停止
        context.stop();
    }
}
