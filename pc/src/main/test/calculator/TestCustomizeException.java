package calculator;

import org.junit.Test;
import util.calculator.CustomizeException;

/**
 * Created by Bruce-Jiang on 2016/9/29.
 */
public class TestCustomizeException {
    @Test
    public void test(){
            try {
                TestClass tc = new TestClass();
                tc.testException();
            } catch (CustomizeException e) {
                e.printStackTrace();
                System.out.println("MsgDes="   +e.getMsgDes());
                System.out.println("RetCd ="    +e.getRetCd());
            }
    }

    /**
     * 内部测试类，用于测试自定义异常
     */
    private class TestClass {

        public void testException() throws CustomizeException {
            try {
                int[] test = new int[10];
                for(int i=0;i<=test.length;i++){
                    System.out.println(i);
                    test[i] = i;
                }
            } catch (Exception e) {
                throw new CustomizeException("14000001", "string's length <4 ");
            }
        }
    }
}

