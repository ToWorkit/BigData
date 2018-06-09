package producer;

import java.io.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ProductLog {
    // 生产数据

    // 时间范围
    private String startTime = "2017-01-01";
    private String endTime = "2018-06-06";

    // 用于存放待随机的电话号码
    private List<String> phoneList = new ArrayList<String>();
    // 每个手机号对应一个姓名
    private Map<String, String> phoneNameMap = new HashMap<String, String>();

    // 准备数据
    public void initPhone() {
        phoneList.add("14213396241");
        phoneList.add("14415731340");
        phoneList.add("14007621689");
        phoneList.add("16802014087");
        phoneList.add("16113357953");
        phoneList.add("17531711595");
        phoneList.add("16319519516");
        phoneList.add("16548161230");
        phoneList.add("19835028834");
        phoneList.add("13669220017");
        phoneList.add("18339177724");
        phoneList.add("15220091119");
        phoneList.add("14415624242");
        phoneList.add("19277831335");
        phoneList.add("18567615224");
        phoneList.add("13062062800");
        phoneList.add("13684972862");
        phoneList.add("19651633755");
        phoneList.add("15641593723");
        phoneList.add("18844563390");

        phoneNameMap.put("14213396241", "李雁");
        phoneNameMap.put("14415731340", "卫艺");
        phoneNameMap.put("14007621689", "仰莉");
        phoneNameMap.put("16802014087", "陶欣悦");
        phoneNameMap.put("16113357953", "施梅梅");
        phoneNameMap.put("17531711595", "金虹霖");
        phoneNameMap.put("16319519516", "魏明艳");
        phoneNameMap.put("16548161230", "华贞");
        phoneNameMap.put("19835028834", "华啟倩");
        phoneNameMap.put("13669220017", "仲采绿");
        phoneNameMap.put("18339177724", "卫丹");
        phoneNameMap.put("15220091119", "戚丽红");
        phoneNameMap.put("14415624242", "何翠柔");
        phoneNameMap.put("19277831335", "钱溶艳");
        phoneNameMap.put("18567615224", "钱琳");
        phoneNameMap.put("13062062800", "缪静欣");
        phoneNameMap.put("13684972862", "焦秋菊");
        phoneNameMap.put("19651633755", "吕访琴");
        phoneNameMap.put("15641593723", "沈丹");
        phoneNameMap.put("18844563390", "褚美丽");
    }

    /**
     * 生产数据
     * 形式: 13684972862,15641593723,2018-03-02 09:13:20,0360
     */
    public String product() {
        // 主叫号码
        String caller = null;
        // 被叫号码(不能和主叫相同)
        String callee = null;

        String callerName = null;
        String calleeName = null;

        // 强转为int 随机下标取手机号
        int callerIndex = (int) (Math.random() * phoneList.size());

        caller = phoneList.get(callerIndex);
        callerName = phoneNameMap.get(caller);
        while (true) {
            int calleeIndex = (int) (Math.random() * phoneList.size());
            callee = phoneList.get(calleeIndex);
            calleeName = phoneNameMap.get(callee);
            if (!caller.equals(callee)) break;
        }
        // 通话日期
        String buildTime = randomBuildTime(startTime, endTime);
        // 将数据格式化为 "0000"
        DecimalFormat df = new DecimalFormat("0000");
        String duration = df.format((int) (30 * 60 * Math.random()));
        // 格式化为string, StringBuilder 连接字符串效率最高
        StringBuilder sb = new StringBuilder();
        sb
                .append(caller + ",")
                .append(callee + ",")
                .append(buildTime + ",")
                .append(duration);
        // 再将 StringBuilder 对象转为字符串
        return sb.toString();
    }

    /**
     * 传入日期区间，建立随机通话日期
     * TS: 时间戳
     * startTimeTS + (endTimeTS - startTimeTS) * Math.random();
     *
     * @param startTime
     * @param endTime
     */
    public String randomBuildTime(String startTime, String endTime) {
        try {
            // 创建格式化时间对象
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            // 使用parse方法转为时间对象格式
            Date startDate = sdf.parse(startTime);
            Date endDate = sdf.parse(endTime);

            if (endDate.getTime() <= startDate.getTime()) return null;

            long randomTs = startDate.getTime() + (long) ((endDate.getTime() - startDate.getTime()) * Math.random());
            // 将时间戳转为时间对象
            Date resultDate = new Date(randomTs);
            // 添加时分秒
            SimpleDateFormat sdf_s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // 转为字符串
            String resultTimeString = sdf_s.format(resultDate);
            return resultTimeString;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将数据写入到文件中
     */
    public void writeLog(String filePath) {
        // 人名为中文涉及到编码所以采用字符流
        // 参数为输出字节流
        try {
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8");
            while (true) {
                Thread.sleep(500);
                // 数据
                String log = product();
                System.out.println(log);
                osw.write(log + "\n");
                // 每次必须手动刷新一下流，不然会缓存到流中然后一次性写入，手动flush可以保证每次都写入数据，保证数据的完整性
                osw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 打包传参
        if (args == null || args.length <= 0) {
            System.out.println("no arguments");
            return;
        }
        String logPath = "D:\\calllog.csv";
        ProductLog productLog = new ProductLog();
        productLog.initPhone();
//        productLog.writeLog(logPath);
        productLog.writeLog(args[0]);
    }
}
