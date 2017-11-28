import java.sql.*;

import com.mysql.jdbc.Driver;

/**
 * <p>
 * Java数据库连接入门
 * </p>
 *
 * @author sqm
 * @version 1.0
 */
public class MySQLTest {
    public static void main(String[] args) throws SQLException {
        //通过sql驱动管理器注册JDBC数据库专用驱动
        DriverManager.registerDriver(new Driver());

        //通过驱动管理器得到连接对象
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","admin");

        //通过连接对象创建创建向数据库发送SQL的申明对象，并发送操作sql的语句
        String sql="select * from user";
        Statement st = con.createStatement();
        //通过SQL声明对象执行SQL语句,并用ResultSet对象接收
        ResultSet result = st.executeQuery(sql);

        //遍历result对象,把SQL语句结果打印在控制台
        System.out.println("+-----------------------------------------+");
        System.out.println("|id  \t|username\t|salary\t\t|job\t\t|");
        //当ResultSet有下一个对象,则一直执行,指针随遍历进行移动
        while (result.next()) {
            int id = result.getInt("id");
            String username = result.getString("username");
            double salary = result.getDouble("salary");
            String job = result.getString("job");
            System.out.println("|" + id + "\t\t|" + username + "\t\t|" + salary + "\t|" + job + "\t\t|");
        }
        System.out.println("+-----------------------------------------+");

        //断开连接释放资源
        result.close();
        st.close();
        con.close();
    }
}
