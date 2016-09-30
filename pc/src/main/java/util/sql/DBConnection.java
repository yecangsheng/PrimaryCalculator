package util.sql;

import org.junit.Test;

import java.io.File;
import java.sql.*;
import java.util.*;

/**
 * Created by Bruce-Jiang on 2016/9/29.
 * 数据库连接类
 * 封装了有参，无参以及存储过程的调用
 * version:V0.0.1
 */
public class DBConnection {
    /**
     *  数据驱动
     */
    private static String driver = null;
    /**
     * 数据库URL
     */
    private static String dbURL = null;
    /**
     * 用户名
     */
    private static String username = null;
    /**
     * 用户密码
     */
    private static String password = null;
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
            Properties properties = ConfigLoad.getInstance().loadConfig(new File(filePath));
            driver = properties.getProperty("driver");
            dbURL = properties.getProperty("dbURL");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
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
     * insert update delete SQL语句的执行的统一方法
     * @param sql SQL语句
     * @param params 参数数组，若没有参数则为null
     * @return 受影响的行数
     */
    public int executeUpdate(String sql, Object[] params) {
        // 受影响的行数
        int affectedLine = 0;

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
            affectedLine = pstmt.executeUpdate();

        } catch (SQLException e) {
            // TODO: 2016/9/30 日志登记缺少
            e.printStackTrace();
        } finally {
            // 释放资源
            closeAll();
        }
        return affectedLine;
    }

    /**
     * SQL 查询将查询结果直接放入ResultSet中
     * @param sql SQL语句
     * @param params 参数数组，若没有参数则为null
     * @return 结果集
     */
    private ResultSet executeQueryRS(String sql, Object[] params) {
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

        } catch (SQLException e) {
            // TODO: 2016/9/30 日志登记缺少
            e.printStackTrace();
        }

        return rs;
    }

    /**
     * SQL 查询将查询结果：一行一列
     * @param sql SQL语句
     * @param params 参数数组，若没有参数则为null
     * @return 结果集
     */
    public Object executeQuerySingle(String sql, Object[] params) {
        Object object = null;
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

            if(rs.next()) {
                object = rs.getObject(1);
            }

        } catch (SQLException e) {
            // TODO: 2016/9/30 日志登记缺少
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return object;
    }

    /**
     * 获取结果集，并将结果放在List中
     *
     * @param sql SQL语句
     * @return List 结果集
     */
    public List<Object> excuteQuery(String sql, Object[] params) {
        // 执行SQL获得结果集
        ResultSet rs = executeQueryRS(sql, params);

        // 创建ResultSetMetaData对象
        ResultSetMetaData rsmd = null;

        // 结果集列数
        int columnCount = 0;
        try {
            rsmd = rs.getMetaData();

            // 获得结果集列数
            columnCount = rsmd.getColumnCount();
        } catch (SQLException e) {
            // TODO: 2016/9/30 日志登记缺少
            e.printStackTrace();
        }

        // 创建List
        List<Object> list = new ArrayList<Object>();

        try {
            // 将ResultSet的结果保存到List中
            while (rs.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    map.put(rsmd.getColumnLabel(i), rs.getObject(i));
                }
                list.add(map);
            }
        } catch (SQLException e) {
            // TODO: 2016/9/30 日志登记缺少
            e.printStackTrace();
        } finally {
            // 关闭所有资源
            closeAll();
        }

        return list;
    }

    /**
     * 存储过程带有一个输出参数的方法
     * @param sql 存储过程语句
     * @param params 参数数组
     * @param outParamPos 输出参数位置
     * @param SqlType 输出参数类型
     * @return 输出参数的值
     */
    public Object excuteQuery(String sql, Object[] params,int outParamPos, int SqlType) {
        Object object = null;
        conn = this.getConnection();
        try {
            // 调用存储过程
            callstmt = conn.prepareCall(sql);

            // 给参数赋值
            if(params != null) {
                for(int i = 0; i < params.length; i++) {
                    callstmt.setObject(i + 1, params[i]);
                }
            }

            // 注册输出参数
            callstmt.registerOutParameter(outParamPos, SqlType);

            // 执行
            callstmt.execute();

            // 得到输出参数
            object = callstmt.getObject(outParamPos);

        } catch (SQLException e) {
            // TODO: 2016/9/30 日志登记缺少
            e.printStackTrace();
        } finally {
            // 释放资源
            closeAll();
        }

        return object;
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
                rs.close();
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
        Connection conn = new DBConnection().getConnection();
        if(conn!=null){
            System.out.println("连接成功");
        }else{
            System.out.println("连接失败");
        }
    }
}

