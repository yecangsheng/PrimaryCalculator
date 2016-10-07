package util.calculator.calculate;

import util.CommonTools;
import util.CustomizeException;

/**
 * Created by Bruce-Jiang on 2016/9/29.
 * 定义一个分数类
 */
public class Fraction {

    private int numerator; // 分子
    private int denominator; // 分母

    /**
     * 空构造方法
     */
    public Fraction() throws CustomizeException{
    }

    /**
     * 待参构造方法
     * @param numerator 分子
     * @param denominator 分母
     */
    public Fraction(int numerator, int denominator) throws CustomizeException{
        if(denominator ==0) {
            this.numerator = numerator;
            this.denominator = denominator;
        }else{
            setNumeratorAndDenominator(numerator,denominator);
        }
    }


    /**
     * 将分数化简，对分子、分母进行约分处理
     *
     * @param num
     *            分子
     * @param deno
     *            分母
     * @throws CustomizeException
     */
    private void setNumeratorAndDenominator(int num, int deno)
            throws CustomizeException { // 设置分子和分母
        if (deno == 0) {
            throw new CustomizeException("分母不能为零！");
        }
        int gcd = CommonTools.gcd(Math.abs(num), Math.abs(deno)); // 计算最大公约数
        numerator = num / gcd;
        denominator = deno / gcd;
        if (numerator < 0 && denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
    }

    public int getNumerator(){
        return numerator;
    }
    public int getDenominator(){return denominator;}

}
