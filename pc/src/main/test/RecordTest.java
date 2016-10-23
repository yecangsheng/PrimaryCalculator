

import entity.Record;
import org.junit.Test;
import service.RecordService;
import service.RecordServiceImp;

import java.util.Date;

/**
 * Created by Bruce-Jiang on 2016/10/23.
 */
public class RecordTest {
    @Test
    public void testInsert(){
        //id,u_id,e_id,score,result,r_time,r_num,r_rank
        //int uId, int eId, String result, String date,int num, int rank
        String date = String.valueOf(System.currentTimeMillis());
        String result = "43";
        Record record = new Record(1, 20000, result, date, 1, 3);
        System.out.println(date);
        RecordService rs = new RecordServiceImp();
        rs.insertOneRecord(record);

    }

}
