package util.shen;

import java.sql.*;

/**
 * Created by shen xing bo on 2016/10/18.
 */
public class SqlOperation {
    Connection conn;
    PreparedStatement preparedStatement;
    ResultSet set;
    public Connection initSqlOperation(){ //初始化数据库的连接对象
        try {
            Class.forName("org.gjt.mm.mysql.Driver").newInstance();
            conn = java.sql.DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pc","root","root");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    public void closeConn(){  //关闭数据库的操作对象
        try{
            conn.close();
        }catch (Exception e){

        }

    }
}
