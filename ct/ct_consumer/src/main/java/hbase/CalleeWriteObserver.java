package hbase;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Durability;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.coprocessor.BaseRegionObserver;
import org.apache.hadoop.hbase.coprocessor.ObserverContext;
import org.apache.hadoop.hbase.coprocessor.RegionCoprocessorEnvironment;
import org.apache.hadoop.hbase.regionserver.wal.WALEdit;
import org.apache.hadoop.hbase.util.Bytes;
import utils.HBaseUtil;
import utils.PropertiesUtil;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CalleeWriteObserver extends BaseRegionObserver {
    /**
     *
     * @param e 表的上下文
     * @param put
     * @param edit
     * @param durability
     * @throws IOException
     */
    @Override
    public void prePut(ObserverContext<RegionCoprocessorEnvironment> e, Put put, WALEdit edit, Durability durability) throws IOException {
        super.prePut(e, put, edit, durability);
        // 1、获取你想要操作的目标表的名称
        String targetTableName = PropertiesUtil.getProperty("hbase.calllog.tablename");
        // 2、获取当前成功put了数据的表(不一定非得是当前业务的操作表)
        String currentTableName = e.getEnvironment().getRegion().getRegionInfo().getRegionNameAsString();
        // 当前协处理器只准应用于当前表
        if(!targetTableName.equals(currentTableName)) return;

        // 01_1323234242_20180401153420_13242342552_1_0340
        String oriRowKey = Bytes.toString(put.getRow());
        // 切分
        String[] splitOriRowKey = oriRowKey.split("_");

        int regions = Integer.valueOf(PropertiesUtil.getProperty("hbase.calllog.regions"));

        String caller = splitOriRowKey[1];
        String callee = splitOriRowKey[3];
        String buildTime = splitOriRowKey[2];
        String flag = "0";
        String duration = splitOriRowKey[5];
        // 生成分区号
        String regionCode = HBaseUtil.genRegionCode(callee, buildTime, regions);
        // 生成rowKey
        String calleeRowKey = HBaseUtil.genRowKey(regionCode, callee, buildTime, caller, flag, duration);

        Put calleePut = new Put(Bytes.toBytes(calleeRowKey));
        calleePut.addColumn(Bytes.toBytes("f2"), Bytes.toBytes("call1"), Bytes.toBytes(callee));
        calleePut.addColumn(Bytes.toBytes("f2"), Bytes.toBytes("call2"), Bytes.toBytes(caller));
        calleePut.addColumn(Bytes.toBytes("f2"), Bytes.toBytes("build_time"), Bytes.toBytes(buildTime));
        calleePut.addColumn(Bytes.toBytes("f2"), Bytes.toBytes("flag"), Bytes.toBytes(flag));
        calleePut.addColumn(Bytes.toBytes("f2"), Bytes.toBytes("duration"), Bytes.toBytes(duration));
        try {
            calleePut.addColumn(Bytes.toBytes("f2"), Bytes.toBytes("build_time_ts"), Bytes.toBytes(new SimpleDateFormat("yyyyMMddHHmmss").parse(buildTime).getTime()));
        } catch (ParseException e1) {
            calleePut.addColumn(Bytes.toBytes("f2"), Bytes.toBytes("build_time_ts"), Bytes.toBytes(""));
            e1.printStackTrace();
        }

        Table table = e.getEnvironment().getTable(TableName.valueOf(targetTableName));
        table.put(calleePut);
        table.close();
    }
}