package service;

import entity.User;

/**
 * Created by Bruce-Jiang on 2016/10/2.
 * 有关用户业务逻辑的抽象方法
 * @version  V0.0.1
 */
public abstract class UserService {
    /**
     * 用户登录
     * @param user
     * @return
     */
    public abstract User login(User user);


    /**
     * 用户注册
     * @param user Model对象
     * @return -1表示该账户已经存在 0 表示失败数据库失败， 1表示成功
     */
    public abstract int register(User user);
}
