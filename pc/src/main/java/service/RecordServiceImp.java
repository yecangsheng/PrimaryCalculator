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
                "e.e_expre as exp" +
                " FROM expression as e, record as r WHERE r.e_id =  e.id" +
                " AND r.u_id = ? AND r_num = ?";
        DBSqlExe dbSqlExe = new DBSqlExe();
        List<Map<String,Object>> mapList = dbSqlExe.exeQueryNTransaction(sql,new Object[]{u_id,num});
        for(Map map : mapList){
            if(!map.isEmpty()){
                ExpRec expRec = new ExpRec();
                expRec.seteId((Integer)map.get("e_id"));
                expRec.setExp((String)map.get("exp"));
                expRec.setResult((String)map.get("result"));
                expRec.setpResult((String)map.get("pResult"));
                expRec.setuId(u_id);
                list.add(expRec);
            }
        }
        return list;
    }

    @Override
    public List<ExpRec> queryForManyRecords(int u_id) {
        List<ExpRec> list = new ArrayList<ExpRec>();
        String sql = "SELECT r.e_id as e_id, r.result as result, e.e_result as pResult," +
                "e.e_expre as exp, r_num as num " +
                " FROM expression as e, record as r WHERE r.e_id =  e.id" +
                " AND r.u_id = ?";
        DBSqlExe dbSqlExe = new DBSqlExe();
        List<Map<String,Object>> mapList = dbSqlExe.exeQueryNTransaction(sql,new Object[]{u_id});
        for(Map map : mapList){
            if(!map.isEmpty()){
                ExpRec expRec = new ExpRec();
                expRec.seteId((Integer)map.get("e_id"));
                expRec.setExp((String)map.get("exp"));
                expRec.setResult((String)map.get("result"));
                expRec.setpResult((String)map.get("pResult"));
                expRec.setNum((Integer)map.get("num"));
                expRec.setuId(u_id);
                list.add(expRec);
            }
        }
        return list;
    }

    @Override
    public long[] queryForResult(int num, int u_id) {
        String errorSql = "SELECT count(*)as error FROM expression as e , record as r WHERE " +
                "  r.u_id = ? AND r.r_num = ?  AND  r.e_id = e.id AND e.e_result <> r.result";
        String correctSql ="SELECT count(*) as correct FROM expression as e , record as r WHERE" +
                "  r.u_id = ? AND r.r_num = ?  AND  r.e_id = e.id AND e.e_result = r.result";
        DBSqlExe dbSqlExe = new DBSqlExe();

        long[] result  = new long[2];
        List<Map<String,Object>> mapList = null;
        mapList= dbSqlExe.exeQueryNTransaction(errorSql,new Object[]{u_id,num});
        if(!mapList.isEmpty()){
            result[1] = (Long) mapList.get(0).get("error");
        }
        mapList = dbSqlExe.exeQueryNTransaction(correctSql,new Object[]{u_id,num});
        if(!mapList.isEmpty()){
            result[0] = (Long) mapList.get(0).get("correct");
        }
        return result;
    }
}
