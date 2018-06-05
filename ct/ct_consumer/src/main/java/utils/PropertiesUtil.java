package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    public static Properties properties = null;
    static {
        // 输入流，读取resource目录的文件
        InputStream is = ClassLoader.getSystemResourceAsStream("hbase_consumer.properties");
        // 将输入流中的数据载入
        properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 定义通过key读取值的方法
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
