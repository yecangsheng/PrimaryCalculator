package util.calculator.calculate;

/**
 * Created by Bruce-Jiang on 2016/9/29.
 * 顶级抽象类，包含一个四则运算方法
 */
public abstract class AbstractCalculator {
    /**
     * 抽象方法，用于统一计算，各种类型的数据以及各种类型的计算
     * @param exp 表达式
     * @return
     */
    public abstract Object arithmetic(String exp);
}
