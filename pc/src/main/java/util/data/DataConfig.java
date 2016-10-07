package util.data;

import entity.Expression;
import org.junit.Test;
import service.ExpressionService;
import service.ExpressionServiceImp;
import util.sql.DBSqlExe;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Bruce-Jiang on 2016/10/4.
 * 数据准备工作，将相应的数据插入到数据表中
 */
public class DataConfig {
    //获取Class文件所在路径
    private final static String configPath = "/data/dataconfig.properties";

    public void writeIntoDB(String filePath, int num) {
        try {
            System.out.println(filePath);
            InputStream inputStream = getClass().getResourceAsStream(filePath);
            Properties properties = new Properties();
            properties.load(inputStream);
            InputStreamReader isr = null;
            int i = 0;
            while(++i<=num){
                String path = (String)properties.get(("rank"+i));
                System.out.println("数量 = "+i+ ":"+path);
                isr = new InputStreamReader(getClass().getResourceAsStream(path));
                StringBuffer sb = new StringBuffer();
                //如果sb的最后一个字符为\n怎么办？需要考虑到这种情况
                for (char[] buffer = new char[1024]; isr.read(buffer) != -1;) {
                    List<Expression> expressionList = null;
                    String cur = new String(buffer).trim();
                    sb.append(cur);
                    if( cur.charAt(cur.length()-1)!= '\n'){
                        String[] strs = sb.toString().split("\n");
                        sb = new StringBuffer();
                        sb.append(strs[strs.length-1]);
                        expressionList = new ArrayList<Expression>();
                        for(int j=0; j<strs.length-1; j++){
                            System.out.println(strs[j]);
                            String[] expString = strs[j].split("=");
                            expressionList.add(new Expression(expString[0],expString[1],i));
                        }
                    }else{
                        String[] strs = sb.toString().split("\n");
                        sb = new StringBuffer();
                        expressionList = new ArrayList<Expression>();
                        for(int j=0; j<strs.length; j++){
                            System.out.println(strs[j]);
                            String[] expString = strs[j].split("=");
                            expressionList.add(new Expression(expString[0],expString[1],i));
                        }
                    }
                    ExpressionService es = new ExpressionServiceImp();
                    es.insertManyExpre(expressionList);
                }
            }
            isr.close();
            inputStream.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    //@Test
    public static void main(String[] args){
        new DataConfig().writeIntoDB(configPath,4);
    }
}
