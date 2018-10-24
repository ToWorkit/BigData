package filter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import java.io.IOException;

/*
 * 常见的过滤器
 * 1、列值过滤器：  select * from emp where sal=3000;
 * 2、列名前缀过滤器：查询员工的姓名   select ename from emp;
 * 3、多个列名前缀过滤器：查询员工的姓名、薪水   select ename,sal from emp;
 * 4、行键过滤器：通过Rowkey查询，类似通过Get查询数据
 * 5、组合几个过滤器查询数据：where 条件1 and（or） 条件2
 */
public class hbaseFilter {
/*    public static void main(String[] args) {
        System.out.println(Bytes.toBytes("name").getClass()); // class [B 字节数组
    }*/

    @Test // 列值过滤器
    public void SelfSingleColumnValueFilter() throws IOException {
        // 列值过滤器
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum", "192.168.220.11");

        // 获取客户端
        HTable table = new HTable(conf, "emp");

        // 定义一个列值过滤器
        /**
         * 列族
         * 列名
         * 比较运算符 相等
         * 值
         */
        SingleColumnValueFilter filter = new SingleColumnValueFilter(Bytes.toBytes("empinfo"), Bytes.toBytes("sal"), CompareFilter.CompareOp.EQUAL, Bytes.toBytes("3000"));

        // 将过滤器加入到扫描器中
        Scan scan = new Scan();
        scan.setFilter(filter);

        // 查询数据
        ResultScanner results = table.getScanner(scan);
        for (Result result : results) {
            // 将二进制数据转为字符串 列族，列名
            String name = Bytes.toString(result.getValue(Bytes.toBytes("empinfo"), Bytes.toBytes("ename")));
            System.out.println("薪资为3000 " + name);
        }
        table.close();
    }

    @Test // 列名前缀过滤器
    public void SelfColumnPrefixFilter() throws IOException {
        // 配置信息
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum", "192.168.220.11");

        // 客户端
        HTable table = new HTable(conf, "emp");

        // 定义列名前缀过滤器
        ColumnPrefixFilter filter = new ColumnPrefixFilter(Bytes.toBytes("ename"));

        // 把过滤器加入到扫描器中
        Scan scan = new Scan();
        scan.setFilter(filter);

        ResultScanner results = table.getScanner(scan);
        System.out.println("---------------");
        for (Result result : results) {
            // 获取姓名和薪水
            /**
             * 只定义了ename
             * SMITH	null
             * ALLEN	null
             */
            String name = Bytes.toString(result.getValue(Bytes.toBytes("empinfo"), Bytes.toBytes("ename")));
            String sal = Bytes.toString(result.getValue(Bytes.toBytes("empinfo"), Bytes.toBytes("sal")));
            System.out.println(name + "\t" + sal);
        }

        // 查询并获取数据

        // 关闭连接
        table.close();
    }

    @Test // 多个列名前缀过滤器
    public void SelfMultipleColumnPrefixFilter() throws IOException {
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum", "192.168.220.11");

        // 客户端
        HTable table = new HTable(conf, "emp");

        // 定义一个二维的字节数组，代表多个列名
        byte[][] names = { Bytes.toBytes("ename"),Bytes.toBytes("sal") };
        // 定义多个列名前缀过滤器，查询   姓名和薪水 参数 byte[][] prefixes
        MultipleColumnPrefixFilter filter = new MultipleColumnPrefixFilter(names);

        // 添加过滤器到扫描器
        Scan scan = new Scan();
        scan.setFilter(filter);

        // 查询结果
        ResultScanner results = table.getScanner(scan);
        for (Result result : results) {
            // 获取姓名和薪水
            String name = Bytes.toString(result.getValue(Bytes.toBytes("empinfo"), Bytes.toBytes("ename")));
            String sal = Bytes.toString(result.getValue(Bytes.toBytes("empinfo"), Bytes.toBytes("sal")));
            System.out.println(name + "\t" + sal);
        }
        table.close();
    }

    @Test // 行键过滤器
    public void SelfRowFilter() throws IOException {
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum", "192.168.220.11");

        // 客户端
        HTable table = new HTable(conf, "emp");

        // 行键(rowkey)过滤器    比较运算符   行键的值
        RowFilter filter = new RowFilter(CompareFilter.CompareOp.EQUAL, new RegexStringComparator("7839"));

        // 把过滤器添加到扫描器中
        Scan scan = new Scan();
        scan.setFilter(filter);

        // 查询数据
        ResultScanner results = table.getScanner(scan);
        for (Result result : results) {
            String name = Bytes.toString(result.getValue(Bytes.toBytes("empinfo"), Bytes.toBytes("ename")));
            String sal = Bytes.toString(result.getValue(Bytes.toBytes("empinfo"), Bytes.toBytes("sal")));

            System.out.println(name + "\t" + sal);
        }
        table.close();
    }

    @Test // 组合几个过滤器
    public void SelfFilter() throws IOException {
        /**
         * 组合 查询工资等于 3000 的员工姓名
         * 列值过滤器
         * 列名前缀过滤器
         */
        //指定的配置信息: ZooKeeper
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum", "192.168.220.11");

        //客户端
        HTable table = new HTable(conf, "emp");

        // 定义一个列值过滤器
        SingleColumnValueFilter filter = new SingleColumnValueFilter(Bytes.toBytes("empinfo"), // 列族
                Bytes.toBytes("sal"),   // 列名
                CompareFilter.CompareOp.EQUAL,  // 比较运算符
                Bytes.toBytes("3000"));// 值

        // 定义一个列名前缀过滤器
        ColumnPrefixFilter filter1 = new ColumnPrefixFilter(Bytes.toBytes("ename"));

        // 创建一个FilterList
        // MUST_PASS_ONE 相当于 or
        FilterList list = new FilterList(FilterList.Operator.MUST_PASS_ALL);// 相当于 and
        list.addFilter(filter);
        list.addFilter(filter1);

        // 将过滤器添加到扫描器
        Scan scan = new Scan();
        scan.setFilter(list);

        // 查询结果
        ResultScanner results = table.getScanner(scan);
        for (Result result : results) {
            // 获取姓名和薪水
            String name = Bytes.toString(result.getValue(Bytes.toBytes("empinfo"), Bytes.toBytes("ename")));
            String sal = Bytes.toString(result.getValue(Bytes.toBytes("empinfo"), Bytes.toBytes("sal")));
            System.out.println(name + "\t" + sal);
        }

        table.close();
    }
}
