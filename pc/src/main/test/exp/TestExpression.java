package exp;

import entity.Expression;
import org.junit.Assert;
import org.junit.Test;
import service.ExpressionService;
import service.ExpressionServiceImp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bruce-Jiang on 2016/10/4.
 * 表达式业务逻辑测试类
 */
public class TestExpression {
    @Test
    public void testInsertOne(){
        Expression exp = new Expression("8 + 5","13",1);
        ExpressionService es = new ExpressionServiceImp();
        int re = es.insertOneExpre(exp);
        Assert.assertEquals(1,re);
    }
    @Test
    public void testInsertMany(){
        List<Expression> list = new ArrayList<Expression>();
        list.add(new Expression("6 + 8", "14",1));
        list.add(new Expression("3 + 5","8",1));
        list.add(new Expression("9 + 9","18",1));
        ExpressionService expressionService = new ExpressionServiceImp();
        int re = expressionService.insertManyExpre(list);
        Assert.assertEquals(3,re);
    }
}
