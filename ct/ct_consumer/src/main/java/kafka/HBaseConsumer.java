package kafka;

import hbase.HBaseDAO;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import utils.PropertiesUtil;

import java.util.Arrays;

/***
 * kafka 消费者
 */
public class HBaseConsumer {
    public static void main(String[] args) {
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(PropertiesUtil.properties);
        // 主题
        kafkaConsumer.subscribe(Arrays.asList(PropertiesUtil.getProperty("kafka.topics")));

        // HBase 逻辑处理在hbase中
        HBaseDAO hd = new HBaseDAO();
        // 一直消费
        while(true) {
            // poll 的延迟时间
            ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
            for(ConsumerRecord<String, String> cr: records) {
                // 原始数据
                String oriValue = cr.value();
                System.out.println(cr.value());
                // 添加
                hd.put(oriValue);
            }
        }
    }
}
