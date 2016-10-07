package util;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bruce-Jiang on 2016/10/3.
 * 表示数据表中的一条记录
 * @version  V0.0.1
 */
public class Record<T extends  Object> {
    private Map<Object,Object> map = null;
    public Record(Class<T> clazz){
        this.map = new HashMap<Object, Object>();
        try{
            Field[] fields = clazz.getDeclaredFields();
            String str = null;
            for (Field field: fields) {
                System.out.println(field.toString());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public Record(Class<T> clazz, ResultSet resultSet){
        this.map = new HashMap<Object, Object>();
        try{
            Field[] fields = clazz.getDeclaredFields();
            String str = null;
            for (Field field: fields) {
                //System.out.println("field = "+field);
                //System.out.println(field.toString());
                String[] strs = field.toString().split("\\.");
                //System.out.println(strs.length);
                //for (String s:strs) {
                //    System.out.println(s);
                //}
                str= strs[strs.length - 1];
                //System.out.println(str);
                //某些属性没有取出来该怎么办？
                map.put(str,resultSet.getObject(str));
            }
            map.put(str,resultSet.getString(str));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public String getString(String columnIndex){
        return map.get(columnIndex).toString();
    }
}
