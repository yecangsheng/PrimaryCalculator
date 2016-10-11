package service;

import entity.Expression;
import util.sql.DBSqlExe;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Bruce-Jiang on 2016/10/4.
 * 表达式业务逻辑实现类
 */
public class ExpressionServiceImp extends ExpressionService{
    @Override
    public int insertManyExpre(List<Expression> mapList) {
        int re = 0;
        for (Expression exp: mapList) {
            int cur = insertOneExpre(exp);
            re += cur;
        }
        return re;
    }

    @Override
    public int insertOneExpre(Expression exp) {
        int re = 0;
        String sqlInsert = "INSERT INTO expression(id, e_expre, e_result, e_rank, e_tip) " +
                "VALUE(null,?,?,?,?)";
        String sqlQuery = "SELECT * FROM expression WHERE e_expre = ? AND e_result = ? AND e_rank = ?";

        DBSqlExe dbSqlExe = new DBSqlExe();

        List<Map<String,Object>> mapList = dbSqlExe.exeQueryNTransaction(sqlQuery,new Object[]{exp.geteExpre(),exp.geteResult(),exp.geteRank()});

        if(mapList.isEmpty()) {
            re = dbSqlExe.exeUpdateTransatcion(sqlInsert, new Object[]{exp.geteExpre(), exp.geteResult(), exp.geteRank(),exp.geteTip()});
        }
        return re;
    }

    @Override
    public Expression obtainOneExp(int rank){
        Random random = new Random();
        String sql = "SELECT * FROM expression WHERE e_rank = ? ORDER BY id";
        //String total = "SELECT COUNT(*) FROM expression WHERE e_rank = ?";
        DBSqlExe dbSqlExe = new DBSqlExe();
        //注意这里是存在败笔的
        if(rank == 0){
            rank = random.nextInt(4)+1;
        }
        List<Map<String,Object>> mapList = dbSqlExe.exeQueryNTransaction(sql,new Object[]{rank});
        int index = random.nextInt(mapList.size());
        Map<String,Object> map = mapList.get(index);
        Expression exp = new Expression();
        exp.setId((Integer) map.get("id"));
        exp.seteExpre((String)map.get("e_expre"));
        exp.seteResult((String)map.get("e_result"));
        exp.seteRank((Integer)map.get("e_rank"));
        exp.seteTip((String)map.get("e_tip"));
        System.out.println(exp.toString());
        return exp;
    }

    @Override
    public List<Expression> obtainManyExp(int rank) {
        return null;
    }
}
