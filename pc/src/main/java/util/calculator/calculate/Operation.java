package util.calculator.calculate;

import util.calculator.CustomizeException;

/**
 * Created by Bruce-Jiang on 2016/9/29.
 * 操作接口，该接口定义了基本的四则运算,启用了泛型编程
 */
public interface Operation<O> {
    /**
     * 加法运算
     * @param obj1
     * @param obj2
     * @return
     */
    public abstract O add(O obj1, O obj2) throws CustomizeException;

    /**
     * 减法运算
     * @param obj1
     * @param obj2
     * @return
     */
    public abstract O substract(O obj1, O obj2) throws CustomizeException;

    /**
     * 乘法运算
     * @param obj1
     * @param obj2
     * @return
     */
    public abstract O multiply(O obj1, O obj2) throws CustomizeException;

    /**
     * 除法运算
     * @param obj1
     * @param obj2
     * @return
     */
    public abstract O divide(O obj1, O obj2) throws CustomizeException;
}
