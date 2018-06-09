package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import utils.HBaseUtil;
import utils.PropertiesUtil;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 初始化
 */
public class HBaseDAO {
    private int regions;
    private String namespace;
    private String tableName;
    public static final Configuration conf;
    private Table table;
    private Connection connection;
    private SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");

    // 代码初始化时就加载
    static {
        conf = HBaseConfiguration.create();
    }

    /**
     * # 分区
     * hbase.calllog.regions=6
     * # 命名空间
     * hbase.calllog.namespace=ns_ct
     * # 表名
     * hbase.calllog.tablename=ns_ct:calllog
     */
    // 初始化表
    public HBaseDAO() {
        try {
            // 分区号
            regions = Integer.valueOf(PropertiesUtil.getProperty("hbase.calllog.regions"));
            // 命名空间
            namespace = PropertiesUtil.getProperty("hbase.calllog.namespace");
            // 表名
            tableName = PropertiesUtil.getProperty("hbase.calllog.tablename");

            // 连接
            connection = ConnectionFactory.createConnection(conf);
            table = connection.getTable(TableName.valueOf(tableName));

            // 不存在则创建
            if (!HBaseUtil.isExistTable(conf, tableName)) {
                HBaseUtil.initNamespace(conf, namespace);
                HBaseUtil.createTable(conf, tableName, regions, "f1");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ori数据样式: 18514587532,185145222211,2018-02-21 23:12:32,1309
     * rowkey样式: 01_18514587532_20180221231232_185145222211_1_1309
     * Hbase表的列: call1 call2 build_time build_time_ts(时间戳) flag duration
     *
     * @param ori
     */
    public void put(String ori) {
        try {

            String[] splitOri = ori.split(",");
            String caller = splitOri[0];
            String callee = splitOri[1];
            String buildTime = splitOri[2];
            String duration = splitOri[3];
            String regionCode = HBaseUtil.genRegionCode(caller, buildTime, regions);
            // parse 转为时间对象
            String buildTimeReplace = sdf2.format(sdf1.parse(buildTime));
            String buildTimeTs = String.valueOf(sdf1.parse(buildTime).getTime());

            // 生成rowkey
            String rowkey = HBaseUtil.genRowKey(regionCode, caller, buildTimeReplace, callee, "1", duration);

            // 向表中插入该条数据
            Put put = new Put(Bytes.toBytes(rowkey));
            put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("call1"), Bytes.toBytes(caller));
            put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("call2"), Bytes.toBytes(callee));
            put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("build_time"), Bytes.toBytes(buildTime));
            put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("build_time_ts"), Bytes.toBytes(buildTimeTs));
            put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("flag"), Bytes.toBytes("1"));
            put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("duration"), Bytes.toBytes(duration));

            table.put(put);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
