package calculator;

import org.junit.Test;
import util.calculator.CustomizeException;
import util.calculator.calculate.Fraction;
import util.calculator.calculate.FractionOperation;
import util.calculator.calculate.Operation;

/**
 * Created by Bruce-Jiang on 2016/9/29.
 * 分数计算测试类
 */
public class TestFractionOperation {
    private static Operation op = new FractionOperation();
    private static Fraction f1 = null ;
    private static Fraction f2 = null ;
    static{
        try{
            f1 = new Fraction(1,5);
            f2 = new Fraction(6,7);
        }catch(CustomizeException e){
            e.printStackTrace();
        }

    }
    @Test
    public void testAdd() throws CustomizeException{
        Fraction re = (Fraction) op.add(f1,f2);
        System.out.println(re.getNumerator()+":"+re.getDenominator());
    }
    @Test
    public void testSubstract() throws CustomizeException{
        Fraction re = (Fraction)op.substract(f1,f2);
        System.out.println(re.getNumerator()+":"+re.getDenominator());
    }
    @Test
    public void testMultiply() throws CustomizeException{
        Fraction re = (Fraction)op.multiply(f1,f2);
        System.out.println(re.getNumerator()+":"+re.getDenominator());
    }
    @Test
    public void testDivide() throws CustomizeException{
        Fraction re = (Fraction)op.divide(f1,f2);
        System.out.println(re.getNumerator()+":"+re.getDenominator());
    }
}
