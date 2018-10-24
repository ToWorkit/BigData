package client;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class HbaseClient {
    public static void main(String[] args) throws IOException {
//        createTable();
//        putData();
//        getData();
//        deleteData();
        dropTable();
    }

    /**
     * 创建表
     */
    private static void createTable() throws IOException {
        // 配置信息，指定zookeeper的地址
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum", "192.168.220.11");

        // 创建hbase的客户端
        HBaseAdmin client = new HBaseAdmin(conf);

        // 通过表描述符创建 students 表
        HTableDescriptor testTable = new HTableDescriptor(TableName.valueOf("testTable"));

        // 指定列族
        testTable.addFamily(new HColumnDescriptor("info"));
        testTable.addFamily(new HColumnDescriptor("grade"));

        // 创建表
        client.createTable(testTable);
        // 关闭客户端
        client.close();
    }

    /**
     * 插入数据
     */
    private static void putData() throws IOException {
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum", "192.168.220.11");

        // 客户端
        HTable client = new HTable(conf, "testTable");

        // 构造Put对象，创建 rowkey
        Put put = new Put(Bytes.toBytes("t01"));

        // 插入数据 列族，列，值
        put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name"), Bytes.toBytes("LP"));

        // 提交操作
        client.put(put);
        // 关闭客户端
        client.close();
    }

    /**
     * 查询数据
     */
    private static void getData() throws IOException {
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum", "192.168.220.11");

        // 创建客户端
        HTable client = new HTable(conf, "testTable");

        // 构建Get对象，指定 rowkey
        Get get = new Get(Bytes.toBytes("t01"));

        // 执行查询 相当于 select * from testTable where rowkey = "t01";
        Result result = client.get(get);

        // 需要将查询到的数据(hbase中没有数据类型，统一都是二进制)转为字符串
        String name = Bytes.toString(result.getValue(Bytes.toBytes("info"), Bytes.toBytes("name")));
        System.out.println("testTable " + name); // testTable LP

        client.close();
    }

    /**
     * 删除数据
     */
    private static void deleteData() throws IOException {
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum", "192.168.220.11");

        // 创建客户端
        HTable client = new HTable(conf, "testTable");

        // 构建Delete对象 指定 rowkey
        Delete delete = new Delete(Bytes.toBytes("t01"));

        // 执行删除操作
        client.delete(delete);

        // 关闭连接
        client.close();
    }

    /**
     * 删除表
     */
    private static void dropTable() throws IOException {
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum", "192.168.220.11");

        // 创建客户端
        HBaseAdmin client = new HBaseAdmin(conf);

        // 先disable再delete
        client.disableTable("testTable");
        client.deleteTable("testTable");

       // 关闭连接
       client.close();
    }
}
