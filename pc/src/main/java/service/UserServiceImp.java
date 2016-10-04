package service;

import entity.User;
import util.sql.DBSqlExe;

import java.util.List;
import java.util.Map;

/**
 * Created by Bruce-Jiang on 2016/10/2.
 * 用户相关业务实现类
 * @version  V0.0.1
 */
public class UserServiceImp extends UserService{
    @Override
    public User login(User user) {
        User user1 = null;
        String sql = "SELECT u_mail as mail, u_nickname as nickname, u_identity as identity FROM user WHERE"
            +" u_mail = ? AND u_password = ? AND user.u_identity = ?";
        DBSqlExe dbConn = new DBSqlExe();
        try{
            List<Map<String,Object>> list = dbConn.exeQueryNTransaction(sql,
                    new Object[]{user.getuMail(),user.getuPassword(),user.getuIdentity()});
            if(list.size()>=1) {
                Map<String, Object> map = list.get(0);
                user1 = new User();
                user1.setuEmail(map.get("mail").toString());
                user1.setuNickname(map.get("nickname").toString());
                user.setuIdentity(Integer.parseInt(map.get("identity").toString()));
            }
        }catch(Exception e){
            e.printStackTrace();
            //日志登记
        }
        return user1;
    }


    @Override
    public int register(User user){
        int re = -1;
        //SQL语句
        String sqlInsert = "INSERT  INTO user(id,u_mail,u_nickname,u_password,u_identity) VALUES"
                 +"(null,?,?,?,?)";
        String sqlQuery = "SELECT * FROM user WHERE u_mail = ? AND u_password = ? AND u_identity = ?";
        DBSqlExe dbse = new DBSqlExe();
        //执行业务逻辑
        //检查该账号是否已经存在过
        List<Map<String,Object>> mapList = dbse.exeQueryNTransaction(sqlQuery,
                new Object[]{user.getuMail(),user.getuPassword(),user.getuIdentity()});
        if(mapList.isEmpty()){
            re = dbse.exeUpdateTransatcion(sqlInsert,new Object[]{user.getuMail(),user.getuNickname(),
                    user.getuPassword(),user.getuIdentity()});
        }
        return re;
    }
}
