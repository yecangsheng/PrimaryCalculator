package user;

import entity.User;
import org.junit.Assert;
import org.junit.Test;
import service.UserService;
import service.UserServiceImp;

/**
 * Created by Bruce-Jiang on 2016/10/2.
 * UserService测试类
 */
public class TestUserServlce {
    @Test
    public void testLogin(){
        User user = new User("cool2016@163.com","123456",1);
        UserService us = new UserServiceImp();
        //表明测试通过
        Assert.assertEquals(0,us.login(user));
    }

    @Test
    public void testRegister(){
        User user = new User("cool@163.com","cool","123456",2);
        UserService us = new UserServiceImp();
        Assert.assertEquals(0,us.register(user));
    }
}
