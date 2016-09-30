package util.calculator.calculate;

import util.CustomizeException;

/**
 * Created by Bruce-Jiang on 2016/9/29.
 * 该类实现了Operation接口，用于分数的基本四则运算
 *
 * 注意：这里有很多冗余，可以简化的
 */
public class FractionOperation implements  Operation {


    public Object add(Object obj1, Object obj2) throws CustomizeException {
        Fraction fr0 =  (Fraction)obj1;
        Fraction fr1 =  (Fraction)obj2;
        int f0n = fr0.getNumerator();
        int f0d = fr0.getDenominator();
        int f1n = fr1.getNumerator();
        int f1d = fr1.getDenominator();
        int n = f0n*f1d+f0d*f1n; //计算分子
        int d = f0d*f1d; //计算分母
        //新建Fraction会抛出例外
        return new Fraction(n,d);
    }

    public Object substract(Object obj1, Object obj2) throws CustomizeException{
        Fraction fr0 =  (Fraction)obj1;
        Fraction fr1 =  (Fraction)obj2;
        int f0n = fr0.getNumerator();
        int f0d = fr0.getDenominator();
        int f1n = fr1.getNumerator();
        int f1d = fr1.getDenominator();
        int n = f0n*f1d-f0d*f1n; //计算分子
        int d = f0d*f1d; //计算分母
        //新建Fraction会抛出例外
        return new Fraction(n,d);
    }

    public Object multiply(Object obj1, Object obj2) throws CustomizeException{
        Fraction fr0 =  (Fraction)obj1;
        Fraction fr1 =  (Fraction)obj2;
        int f0n = fr0.getNumerator();
        int f0d = fr0.getDenominator();
        int f1n = fr1.getNumerator();
        int f1d = fr1.getDenominator();
        int n = f0n*f1n; //计算分子
        int d = f0d*f1d; //计算分母
        //新建Fraction会抛出例外
        return new Fraction(n,d);
    }

    public Object divide(Object obj1, Object obj2) throws CustomizeException{

        Fraction fr0 =  (Fraction)obj1;
        Fraction fr1 =  (Fraction)obj2;
        if(fr1.getDenominator() == 0){
            throw new CustomizeException("被除数不能为零！！！");
        }
        int f0n = fr0.getNumerator();
        int f0d = fr0.getDenominator();
        int f1n = fr1.getNumerator();
        int f1d = fr1.getDenominator();
        int n = f0n*f1d; //计算分子
        int d = f0d*f1n; //计算分母
        //新建Fraction会抛出例外
        return new Fraction(n,d);
    }


}
