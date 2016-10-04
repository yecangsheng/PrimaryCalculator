package util.sql;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Bruce-Jiang on 2016/9/30.
 * 数据库连接参数加载类,本类是线程安全的
 * @version  V0.0.01
 */
public final class ConfigLoad {
    private static ConfigLoad instance = new ConfigLoad();
    private ConfigLoad(){}
    public static ConfigLoad getInstance(){
        return instance;
    }
    public Properties loadConfig(InputStream inputStream){
        Properties properties = new Properties();
        try{
            properties.load(inputStream);
        }catch(IOException e){
            e.printStackTrace();
            //登记日志
        }
        return properties;
    }
}
