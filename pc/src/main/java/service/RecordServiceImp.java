package service;

import entity.ExpRec;
import entity.Record;
import util.sql.DBSqlExe;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by Bruce-Jiang on 2016/10/9.
 */
public class RecordServiceImp extends RecordService{
    @Override
    public int insertOneRecord(Record record) {
        String sql = "INSERT INTO record(id,u_id,e_id,result,r_time,r_num,r_rank)" +
                " VALUES(null,?,?,?,?,?,?)";
        DBSqlExe dbSqlExe = new DBSqlExe();
        int re  = dbSqlExe.exeUpdateTransatcion(sql,
                new Object[]{record.getuId(),record.geteId(),record.getResult(),
                record.getDate(),record.getNum(),record.getR_rank()});
        return re;
    }

    @Override
    public int queryForFrequency(int u_id) {
        int num = 0;
        String sql = "SELECT max(r_num) as num FROM record WHERE u_id = ?";
        DBSqlExe dbSqlExe = new DBSqlExe();
        List<Map<String,Object>> maps  = dbSqlExe.exeQueryNTransaction(sql,
                new Object[]{u_id});
        if(!maps.isEmpty()){
            Object str = maps.get(0).get("num");
            num = Integer.parseInt((str==null || str.equals(""))? "0" : str.toString());
        }
        return num;
    }

    @Override
    public List<ExpRec> queryForManyRecords(int num, int u_id) {
        List<ExpRec> list = new ArrayList<ExpRec>();
        String sql = "SELECT r.e_id as e_id, r.result as result, e.e_result as pResult," +
                "e.expre as exp" +
                " FROM expression as e, record as r WHERE r.e_id =  e.id" +
                " AND r.u_id = ? AND r_num = ?";
        DBSqlExe dbSqlExe = new DBSqlExe();
        List<Map<String,Object>> mapList = dbSqlExe.exeQueryNTransaction(sql,new Object[]{num,u_id});
        for(Map map : mapList){
            if(!map.isEmpty()){
                ExpRec expRec = new ExpRec();
               // expRec.seteId();
            }
        }

        return list;
    }
}
