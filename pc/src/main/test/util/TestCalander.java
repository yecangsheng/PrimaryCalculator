package util;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Bruce-Jiang on 2016/10/9.
 */
public class TestCalander {
    @Test
    public void test(){
        Date date = new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(date));
        System.out.println(Calendar.getInstance().getTime());
        System.out.println(new java.util.Date(Calendar.getInstance().getTimeInMillis()));
    }
}
