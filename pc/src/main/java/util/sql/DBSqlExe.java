package util.sql;

import org.junit.Test;
import util.Record;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Bruce-Jiang on 2016/9/29.
 * 数据库连接类
 * 封装了有参，无参以及存储过程的调用
 * version:V0.0.1
 */
public class DBSqlExe {
    /**
     *  数据驱动
     */
    private static String driver = "com.mysql.jdbc.Driver";
    /**
     * 数据库URL
     */
    private static String dbURL = "jdbc:mysql://127.0.0.1:3306/pc";
    /**
     * 用户名
     */
    private static String username = "root";
    /**
     * 用户密码
     */
    private static String password = "root";
    /**
     * 配置文件路径,如此之长!是一个待解决的问题
     */
    private static String filePath = "src/main/java/util/sql/sqlconfig.properties";

    /**
     * 数据库连接对象
     */
    private Connection conn = null;
    /**
     *预编译PreparedStatement对象
     */
    private PreparedStatement pstmt = null;
    /**
     * 结果集对象
     */
    private ResultSet rs = null;
    /**
     * 存储过程声明对象
     */
    private CallableStatement callstmt = null;
    static{
        try{
          //  InputStream is=DBSqlExe.class.getClassLoader().getResourceAsStream("sqlconfig.properties");
         //   Properties properties = ConfigLoad.getInstance().loadConfig(is);
         //   driver = properties.getProperty("driver");
         //   dbURL = properties.getProperty("dbURL");
         //   username = properties.getProperty("username");
         //   password = properties.getProperty("password");
           // System.out.println(driver);
           // System.out.println(dbURL);
           // System.out.println(username);
           // System.out.println(password);
            //加载驱动
            Class.forName(driver);
        }catch(Exception e){
            // TODO: 2016/9/30 日志登记缺少
            e.printStackTrace();
        }
    }

    /**
     * 建立数据库连接
     * @return 数据库连接
     */
    public Connection getConnection() {
        try {
            // 获取连接
            conn = DriverManager.getConnection(dbURL, username,
                    password);
        } catch (SQLException e) {
            // TODO: 2016/9/30 日志登记缺少
            e.printStackTrace();
        }
        return conn;
    }


    /**
     * SQL 查询将查询结果直接放入ResultSet中
     * 使用PreparedStatement对象,不执行事物
     * @param sql SQL语句
     * @param params 参数数组，若没有参数则为null
     * @return 结果列表
     */
    public  List<Map<String,Object>> exeQueryNTransaction(String sql, Object[] params) {
        // 创建ResultSetMetaData对象
        ResultSetMetaData rsmd = null;
        // 结果集列数
        int columnCount = 0;

        List<Map<String,Object>> reList = null;
        try {
            // 获得连接
            conn = this.getConnection();
            // 调用SQL
            pstmt = conn.prepareStatement(sql);

            // 参数赋值
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
            }
            // 执行
            rs = pstmt.executeQuery();

            rsmd = rs.getMetaData();
            // 获得结果集列数
            columnCount = rsmd.getColumnCount();

            reList = new ArrayList<Map<String, Object>>();

            //处理结果集
            while(rs.next()){
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                   // System.out.println(rsmd.getColumnLabel(i));
                    map.put(rsmd.getColumnLabel(i), rs.getObject(i));
                }
                reList.add(map);
            }
        } catch (SQLException e) {
            // TODO: 2016/9/30 日志登记缺少
            e.printStackTrace();
        }finally {
            closeAll();
        }
        return reList;
    }

    /**
     * SQL 查询将查询结果直接放入ResultSet中
     * 使用PreparedStatement对象,执行事物
     * @param sql SQL语句
     * @param params 参数列表
     * @return 结果集
     *
     */
    public List<Map<String,Object>> exeQueryTransaction(String sql, Object[] params){
        // 创建ResultSetMetaData对象
        ResultSetMetaData rsmd = null;
        // 结果集列数
        int columnCount = 0;

        List<Map<String,Object>> mapList = null;
        try{
            //获取数据库连接
            conn = this.getConnection();

            //设置手动提交事务
            conn.setAutoCommit(false);

            // 调用SQL
            pstmt = conn.prepareStatement(sql);

            // 参数赋值
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
            }
            rs = pstmt.executeQuery();

            rsmd = rs.getMetaData();
            // 获得结果集列数
            columnCount = rsmd.getColumnCount();

            mapList = new ArrayList<Map<String, Object>>();

            //处理结果集
            while(rs.next()){
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    // System.out.println(rsmd.getColumnLabel(i));
                    map.put(rsmd.getColumnLabel(i), rs.getObject(i));
                }
                mapList.add(map);
            }
            conn.commit();
        }catch(SQLException e){
            try {
                //回滚事务
                conn.rollback();
            }catch (SQLException el){
                e.printStackTrace();
            }
            e.printStackTrace();

            //登记日志
        }finally {
            closeAll();
        }
        return mapList;
    }

    /**
     * 事物执行添加,修改，删除语句，统称为为更新
     * @param sql SQL语句
     * @param params 参数列表
     * @return 执行结果为SQL语句影响了几条记录
     */
    public int exeUpdateTransatcion(String sql, Object[] params){
        int re = 0;
        try{
            //获得数据库连接
            conn = this.getConnection();

            //设置事物手动提交
            conn.setAutoCommit(false);

            //执行预编译语句
            pstmt = conn.prepareStatement(sql);

            //添加参数
            if(params != null){
                for(int i=0; i<params.length; i++){
                    pstmt.setObject(i+1, params[i]);
                }
            }

            //结果集
            re = pstmt.executeUpdate();
            //提交事务
            conn.commit();
        }catch(SQLException e){
            try{
                //回滚事务
                conn.rollback();
            }catch(SQLException el){
                e.printStackTrace();
            }
            e.printStackTrace();
            //登记日志
        }finally {
            closeAll();
        }
        return re;
    }
    /**
     * 关闭所有资源
     */
    private void closeAll() {
        // 关闭结果集对象
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO: 2016/9/30 日志登记缺少
                e.printStackTrace();
            }
        }
        // 关闭PreparedStatement对象
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                // TODO: 2016/9/30 日志登记缺少
                e.printStackTrace();
            }
        }
        // 关闭CallableStatement 对象
        if (callstmt != null) {
            try {
                callstmt.close();
            } catch (SQLException e) {
                // TODO: 2016/9/30 日志登记缺少
                e.printStackTrace();
            }
        }
        // 关闭Connection 对象
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO: 2016/9/30 日志登记缺少
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭所有资源
     */
    public void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        // 关闭结果集对象
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO: 2016/9/30 日志登记缺少
                e.printStackTrace();
            }
        }
        // 关闭PreparedStatement对象
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                // TODO: 2016/9/30 日志登记缺少
                e.printStackTrace();
            }
        }
        // 关闭CallableStatement 对象
        if (callstmt != null) {
            try {
                callstmt.close();
            } catch (SQLException e) {
                // TODO: 2016/9/30 日志登记缺少
                e.printStackTrace();
            }
        }
        // 关闭Connection 对象
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO: 2016/9/30 日志登记缺少
                e.printStackTrace();
            }
        }
    }
    @Test
    public void testConnection(){
        Connection conn = new DBSqlExe().getConnection();
        if(conn!=null){
            System.out.println("连接成功");
        }else{
            System.out.println("连接失败");
        }
    }
}

