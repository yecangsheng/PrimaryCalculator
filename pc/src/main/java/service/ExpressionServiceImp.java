package service;

import entity.Expression;
import util.sql.DBSqlExe;

import java.util.List;
import java.util.Map;

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
        String sqlInsert = "INSERT INTO expression(id, e_expre, e_result, e_rank) " +
                "VALUE(null,?,?,?)";
        String sqlQuery = "SELECT * FROM expression WHERE e_expre = ? AND e_result = ? AND e_rank = ?";

        DBSqlExe dbSqlExe = new DBSqlExe();

        List<Map<String,Object>> mapList = dbSqlExe.exeQueryNTransaction(sqlQuery,new Object[]{exp.geteExpre(),exp.geteResult(),exp.geteRank()});

        if(mapList.isEmpty()) {
            re = dbSqlExe.exeUpdateTransatcion(sqlInsert, new Object[]{exp.geteExpre(), exp.geteResult(), exp.geteRank()});
        }
        return re;
    }


}
