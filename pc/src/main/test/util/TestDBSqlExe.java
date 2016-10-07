package util;

import entity.User;
import org.junit.Test;
import util.sql.DBSqlExe;

import java.util.List;
import java.util.Map;

/**
 * Created by Bruce-Jiang on 2016/10/4.
 * 数据库操作测试类
 */
public class TestDBSqlExe {
    @Test
    //
    public void test(){
        String sql = "SELECT u_mail as mail, u_nickname as nickname, u_identity as identity FROM user WHERE"
                +" u_mail = ? AND u_password = ? AND user.u_identity = ?";
        DBSqlExe sbse = new DBSqlExe();
        List<Map<String,Object>> list = sbse.exeQueryNTransaction(sql,new Object[]{"cool2016@163.com","123456",1});
        for (Map map: list) {
            System.out.println(map.get("mail"));
            System.out.println(map.get("nickname"));
            System.out.println(map.get("identity"));
        }
    }

   // @Test
    public void testString(){
        String[] strs = "private int entity.User.id".split(" ");

        for (String str:strs) {
            System.out.println(str);
        }

        String[] strs1 = "entity.User.id".split("\\.");
        for (String str:strs1) {
            System.out.println(str);
        }
    }
}
