package calculator;

import org.junit.Test;
import util.calculator.calculate.AbstractCalculator;
import util.calculator.calculate.GeneralCalculator;

/**
 * Created by Bruce-Jiang on 2016/9/29.
 *
 */
public class CalculatorTest {
    @Test
    public void test(){
        AbstractCalculator ac = new GeneralCalculator();
        //验证 接受
        String exp1 = "2+((3+4)*2-22)/2*3";
        String re1 = ac.arithmetic(exp1).toString();
        System.out.println(re1);
        //验证不接受
        //String exp2 = "-2+10*6-(3+3-4)*5/2";
        // String re2 = ac.arithmetic(exp2).toString();
        //System.out.println(re2);
        //验证接受
        String exp3 = "2+10*6-(3+3-4)*5/2";
        String re3 = ac.arithmetic(exp3).toString();
        System.out.println(re3);
        //验证不接受
		// String exp4 = "2000+10*6-(3+3-4)*5/2+ 2^2";
		// String re4 = ac.arithmetic(exp4).toString();
		// System.out.println(re4);

        String exp5 = "2+10*6-(3+3-4)*5/2";
        String re5 = ac.arithmetic(exp5).toString();
        System.out.println(re5);

    }
}
