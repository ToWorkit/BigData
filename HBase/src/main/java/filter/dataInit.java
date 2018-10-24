package filter;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

public class dataInit {
    @Test
    public void testCreateTable() throws Exception{
        //指定的配置信息: ZooKeeper
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum", "192.168.220.11");

        //创建一个HBase客户端: HBaseAdmin
        HBaseAdmin admin = new HBaseAdmin(conf);

        //创建一个表的描述符: 表名
        HTableDescriptor hd = new HTableDescriptor(TableName.valueOf("emp"));

        //创建列族描述符
        HColumnDescriptor hcd1 = new HColumnDescriptor("empinfo");

        //加入列族
        hd.addFamily(hcd1);

        //创建表
        admin.createTable(hd);

        //关闭客户端
        admin.close();
    }

    @Test
    public void testPutData() throws Exception{
        //指定的配置信息: ZooKeeper
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum", "192.168.220.11");

        //客户端
        HTable table = new HTable(conf, "emp");

        //第一条数据
        Put put1 = new Put(Bytes.toBytes("7369"));
        put1.add(Bytes.toBytes("empinfo"), Bytes.toBytes("ename"), Bytes.toBytes("SMITH"));
        Put put2 = new Put(Bytes.toBytes("7369"));
        put2.add(Bytes.toBytes("empinfo"), Bytes.toBytes("sal"), Bytes.toBytes("800"));

        //第二条数据
        Put put3 = new Put(Bytes.toBytes("7499"));
        put3.add(Bytes.toBytes("empinfo"), Bytes.toBytes("ename"), Bytes.toBytes("ALLEN"));
        Put put4 = new Put(Bytes.toBytes("7499"));
        put4.add(Bytes.toBytes("empinfo"), Bytes.toBytes("sal"), Bytes.toBytes("1600"));

        //第三条数据
        Put put5 = new Put(Bytes.toBytes("7521"));
        put5.add(Bytes.toBytes("empinfo"), Bytes.toBytes("ename"), Bytes.toBytes("WARD"));
        Put put6 = new Put(Bytes.toBytes("7521"));
        put6.add(Bytes.toBytes("empinfo"), Bytes.toBytes("sal"), Bytes.toBytes("1250"));

        //第四条数据
        Put put7 = new Put(Bytes.toBytes("7566"));
        put7.add(Bytes.toBytes("empinfo"), Bytes.toBytes("ename"), Bytes.toBytes("JONES"));
        Put put8 = new Put(Bytes.toBytes("7566"));
        put8.add(Bytes.toBytes("empinfo"), Bytes.toBytes("sal"), Bytes.toBytes("2975"));

        //第五条数据
        Put put9 = new Put(Bytes.toBytes("7654"));
        put9.add(Bytes.toBytes("empinfo"), Bytes.toBytes("ename"), Bytes.toBytes("MARTIN"));
        Put put10 = new Put(Bytes.toBytes("7654"));
        put10.add(Bytes.toBytes("empinfo"), Bytes.toBytes("sal"), Bytes.toBytes("1250"));

        //第六条数据
        Put put11 = new Put(Bytes.toBytes("7698"));
        put11.add(Bytes.toBytes("empinfo"), Bytes.toBytes("ename"), Bytes.toBytes("BLAKE"));
        Put put12 = new Put(Bytes.toBytes("7698"));
        put12.add(Bytes.toBytes("empinfo"), Bytes.toBytes("sal"), Bytes.toBytes("2850"));

        //第七条数据
        Put put13 = new Put(Bytes.toBytes("7782"));
        put13.add(Bytes.toBytes("empinfo"), Bytes.toBytes("ename"), Bytes.toBytes("CLARK"));
        Put put14 = new Put(Bytes.toBytes("7782"));
        put14.add(Bytes.toBytes("empinfo"), Bytes.toBytes("sal"), Bytes.toBytes("2450"));

        //第八条数据
        Put put15 = new Put(Bytes.toBytes("7788"));
        put15.add(Bytes.toBytes("empinfo"), Bytes.toBytes("ename"), Bytes.toBytes("SCOTT"));
        Put put16 = new Put(Bytes.toBytes("7788"));
        put16.add(Bytes.toBytes("empinfo"), Bytes.toBytes("sal"), Bytes.toBytes("3000"));

        //第九条数据
        Put put17 = new Put(Bytes.toBytes("7839"));
        put17.add(Bytes.toBytes("empinfo"), Bytes.toBytes("ename"), Bytes.toBytes("KING"));
        Put put18 = new Put(Bytes.toBytes("7839"));
        put18.add(Bytes.toBytes("empinfo"), Bytes.toBytes("sal"), Bytes.toBytes("5000"));

        //第十条数据
        Put put19 = new Put(Bytes.toBytes("7844"));
        put19.add(Bytes.toBytes("empinfo"), Bytes.toBytes("ename"), Bytes.toBytes("TURNER"));
        Put put20 = new Put(Bytes.toBytes("7844"));
        put20.add(Bytes.toBytes("empinfo"), Bytes.toBytes("sal"), Bytes.toBytes("1500"));

        //第十一条数据
        Put put21 = new Put(Bytes.toBytes("7876"));
        put21.add(Bytes.toBytes("empinfo"), Bytes.toBytes("ename"), Bytes.toBytes("ADAMS"));
        Put put22 = new Put(Bytes.toBytes("7876"));
        put22.add(Bytes.toBytes("empinfo"), Bytes.toBytes("sal"), Bytes.toBytes("1100"));

        //第十二条数据
        Put put23 = new Put(Bytes.toBytes("7900"));
        put23.add(Bytes.toBytes("empinfo"), Bytes.toBytes("ename"), Bytes.toBytes("JAMES"));
        Put put24 = new Put(Bytes.toBytes("7900"));
        put24.add(Bytes.toBytes("empinfo"), Bytes.toBytes("sal"), Bytes.toBytes("950"));

        //第十三条数据
        Put put25 = new Put(Bytes.toBytes("7902"));
        put25.add(Bytes.toBytes("empinfo"), Bytes.toBytes("ename"), Bytes.toBytes("FORD"));
        Put put26 = new Put(Bytes.toBytes("7902"));
        put26.add(Bytes.toBytes("empinfo"), Bytes.toBytes("sal"), Bytes.toBytes("3000"));

        //第十四条数据
        Put put27 = new Put(Bytes.toBytes("7934"));
        put27.add(Bytes.toBytes("empinfo"), Bytes.toBytes("ename"), Bytes.toBytes("MILLER"));
        Put put28 = new Put(Bytes.toBytes("7934"));
        put28.add(Bytes.toBytes("empinfo"), Bytes.toBytes("sal"), Bytes.toBytes("1300"));

        //构造List
        List<Put> list = new ArrayList<Put>();
        list.add(put1);
        list.add(put2);
        list.add(put3);
        list.add(put4);
        list.add(put5);
        list.add(put6);
        list.add(put7);
        list.add(put8);
        list.add(put9);
        list.add(put10);
        list.add(put11);
        list.add(put12);
        list.add(put13);
        list.add(put14);
        list.add(put15);
        list.add(put16);
        list.add(put17);
        list.add(put18);
        list.add(put19);
        list.add(put20);
        list.add(put21);
        list.add(put22);
        list.add(put23);
        list.add(put24);
        list.add(put25);
        list.add(put26);
        list.add(put27);
        list.add(put28);

        //插入数据
        table.put(list);
        table.close();
    }
}
