package service;

import entity.Record;
import util.sql.DBSqlExe;

import java.sql.Time;


/**
 * Created by Bruce-Jiang on 2016/10/9.
 */
public class RecordServiceImp extends RecordService{
    @Override
    public int insertOneRecord(Record record) {
        String sql = "INSERT INTO record(id,u_id,e_id,result,r_time)" +
                " VALUES(null,?,?,?,?)";
        DBSqlExe dbSqlExe = new DBSqlExe();
        int re  = dbSqlExe.exeUpdateTransatcion(sql,
                new Object[]{record.getuId(),record.geteId(),record.getResult(),
                record.getDate()});
        return re;
    }
}
