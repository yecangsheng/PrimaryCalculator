package util.data;

import entity.Expression;
import service.ExpressionService;
import service.ExpressionServiceImp;
import util.sql.DBSqlExe;

import java.io.*;
import java.net.URISyntaxException;
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
           // InputStreamReader isr = null;
            BufferedReader br = null;
            int i = 0;
            while(++i<=num){
                String path = (String)properties.get(("rank"+i));
                System.out.println("数量 = "+i+ ":"+path);
                //System.out.println(getClass().getResource(path).getFile());
                br = new BufferedReader(new FileReader(getClass().getResource(path).getFile()));
                //isr = new InputStreamReader(getClass().getResourceAsStream(path));
                String line = null;
                ExpressionService es = new ExpressionServiceImp();
                while((line = br.readLine())!=null){
                    System.out.println(line);
                    String[] parts = line.split("=");
                    es.insertOneExpre(new Expression(parts[0].trim(),parts[1].trim(),i,null));
                }
                //如果sb的最后一个字符为\n怎么办？需要考虑到这种情况
                //for (char[] buffer = new char[1024]; isr.read(buffer) != -1;) {
                //    List<Expression> expressionList = null;
               //     String cur = new String(buffer).trim();
                //    sb.append(cur);
                //    if( cur.charAt(cur.length()-1)!= '\n'){
                //        String[] strs = sb.toString().split("\n");
                 //       sb = new StringBuffer();
                 //       sb.append(strs[strs.length-1]);
                 //       expressionList = new ArrayList<Expression>();
                //        for(int j=0; j<strs.length-1; j++){
                //            System.out.println(strs[j]);
                 //           String[] expString = strs[j].split("=");
                  //          expressionList.add(new Expression(expString[0],expString[1],i,null));
                  //      }
                 //   }else{
                 //       String[] strs = sb.toString().split("\n");
                  //      sb = new StringBuffer();
                  //      expressionList = new ArrayList<Expression>();
                 //       for(int j=0; j<strs.length; j++){
                  //          System.out.println(strs[j]);
                 //           String[] expString = strs[j].split("=");
                  //          expressionList.add(new Expression(expString[0],expString[1],i,null));
                  //      }
                 //   }

               // }
            }
           // isr.close();
            br.close();
            inputStream.close();

        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    //@Test
    public static void main(String[] args){
        new DataConfig().writeIntoDB(configPath,4);
    }
}
