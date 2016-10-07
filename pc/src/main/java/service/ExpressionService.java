package service;

import entity.Expression;

import java.util.List;

/**
 * Created by Bruce-Jiang on 2016/10/4.
 */
public abstract class ExpressionService {
    /**
     * 一次性插入多条记录,注意这里是需要修改的，因为其中如果有一个是重复的
     * 以上已经插入的是不会回滚的，只会回滚当前一条记录，并且剩下的记录仍旧会继续
     * 插入，返回值只会显示一共插入了几条数据，如果插入的条数小于初始给予，
     * 可能的情况有两种有重复或者说有插入失败，如果插入失败将会有例外抛出
     * @param mapList Exression序列
     * @return 插入条数
     */
    public abstract int insertManyExpre(List<Expression> mapList);

    /**
     * 一次仅仅插入一条记录
     * @param expression Expression对象
     * @return
     */
    public abstract int insertOneExpre(Expression expression);
}
