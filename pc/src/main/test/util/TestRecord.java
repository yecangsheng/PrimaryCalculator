package util;

import entity.User;
import org.junit.Test;

/**
 * Created by Bruce-Jiang on 2016/10/3.
 */
public class TestRecord {
    @Test
    public void testTest(){
        new Record<User>(User.class);
    }
}
