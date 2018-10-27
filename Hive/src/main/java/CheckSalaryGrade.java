import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * 把jar加入Hive的Classpath
 * 	add jar /root/temp/xx.jar;
 * 为自定义函数创建别名
 *  create temporary function xx as 'xx.xx'; (函数名)
 * 使用
 *  select xx(x) from table;
 */
public class CheckSalaryGrade extends UDF {
    // 重写的方法名必须是 evaluate
    public String evaluate(String salary) {
        int sal = Integer.parseInt(salary);

        if (sal < 1000) return "C";
        else if (sal >= 1000 && sal < 3000) return "B";
        else return "A";
    }
}
