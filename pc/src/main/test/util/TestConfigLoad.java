package util;

import org.junit.Test;
import util.sql.ConfigLoad;

import java.io.File;
import java.util.Properties;

/**
 * Created by Bruce-Jiang on 2016/9/30.
 * ConfigLoad测试类
 */
public class TestConfigLoad {
    @Test
    public void testConfigLoad(){
        String filePath = "F:\\source\\git_work_space\\PrimaryCalculator\\pc\\src\\main\\java\\util\\sql\\sqlconfig.properties";
        Properties properties = ConfigLoad.getInstance().loadConfig(new File(filePath));
        System.out.println(properties.toString());
    }
}
