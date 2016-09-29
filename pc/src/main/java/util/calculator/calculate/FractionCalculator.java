package util.calculator.calculate;

import util.calculator.CustomizeException;

import java.util.*;

/**
 * Created by Bruce-Jiang on 2016/9/29.
 * 算法解释：
 *    在这里我将特殊符号&定义为分数中分子和分母的分线
 */
public class FractionCalculator extends AbstractCalculator{
    @Override
    public Object arithmetic(String exp){
        Stack<Fraction> fStack = new Stack<Fraction>();
        Stack<String> oStack = new Stack<String>();
        oStack.add("#");
        try {
            Map<Integer,Object> map = expPretreatment(exp);
            map.put(map.size(),"#");
            int i=0;
            Object obj = map.get(i);
            while(!obj.equals("#") || !oStack.peek().equals("#")){
                if(!isOperator(obj)){
                    fStack.push((Fraction)obj);
                    obj = map.get(++i);
                    //System.out.println("执行了");
                }else{
                    //比较栈顶操作符和新取得的操作符的优先关系
                    switch (compareOperPriority(oStack.peek(), (String)obj))
                    {
                        case '<'://栈顶优先权低
                            oStack.push((String)obj);
                            obj = map.get(++i);
                            break;
                        case '='://括号配对，栈顶括号弹出
                            oStack.pop();
                            obj = map.get(++i);
                            break;
                        case '>'://栈顶优先权高，先弹出，计算，结果操作数入栈
                            String op = oStack.peek();
                            oStack.pop();
                            Fraction num2 = fStack.peek();//第二个操作数在前
                            fStack.pop();
                            Fraction num1 = fStack.peek();
                            fStack.pop();
                            Fraction ret = operation(num1, op, num2);
                            System.out.println(ret.getNumerator()+":"+ret.getDenominator());
                            fStack.push(ret);
                            break;
                    }
                }
            }
        }catch(CustomizeException e){
            e.printStackTrace();
            //输出到日志
        }
        return fStack.peek();
    }

    //运算符的优先关系
    private static final char[][] oprRelation =   {
            //'+', '-', '*', '/', '^', (', ')', '#'
            {'>', '>', '<', '<', '<', '<', '>', '>'}, //'+'
            {'>', '>', '<', '<', '<', '<', '>', '>'}, //'-'
            {'>', '>', '>', '>', '<', '<', '>', '>'}, //'*'
            {'>', '>', '>', '>', '<', '<', '>', '>'}, //'/'
            {'>', '>', '>', '>', '<', '<', '>', '>'}, //'^'
            {'<', '<', '<', '<', '<', '<', '=', '>'}, //'('
            {'>', '>', '>', '>', '>', '=', '>', '>'}, //')'
            {'<', '<', '<', '<', '<', '<', ' ', '='}  //'#'
    };

    /**
     * 运算符代表下标
     * @param oper
     * @return 下标索引
     */
    private static int getIndex(String oper){
        int index = 0;
        if(oper.equals("+")) {
            index = 0;
        }
        if(oper.equals("-")) {
            index = 1;
        }
        if(oper.equals("*")) {
            index = 2;
        }
        if(oper.equals("/")) {
            index = 3;
        }
        if(oper.equals("^")){
            index = 4;
        }
        if(oper.equals("(")) {
            index = 5;
        }
        if(oper.equals(")")){
            index = 6;
        }
        if(oper.equals("#")){
            index = 7;
        }
        return index;
    }

    /**
     * 比较符号优先级
     * @param oper1
     * @param oper2
     * @return
     */
    private static char compareOperPriority(String oper1, String oper2){
        return oprRelation[getIndex(oper1)][getIndex(oper2)];
    }


    /**
     *  表达式的形式如下：
     *   3 + 1 - 3 + ( 3 - 1 ) * 5 / 2 + 6&2
     * @param exp 表达式
     * @return 返回值 List<Object> 为表达式
     * @throws CustomizeException
     */
    private Map<Integer,Object> expPretreatment(String exp) throws CustomizeException {
        Map<Integer,Object> map = new HashMap<Integer, Object>();
        String[] strings = exp.split(" ");
        int i=-1;
        for (String str: strings) {
            if(isOperator(str)){
                map.put(++i,str);
            }else{
                if(!str.contains("&")){
                    System.out.println(str.toString());
                    map.put(++i,new Fraction(Integer.parseInt(str),1));
                    System.out.println("cool");
                }else{
                    String[] strs = str.split("&");
                    map.put(++i,new Fraction(Integer.parseInt(strs[0]),Integer.parseInt(strs[1])));
                }
            }
        }
        return map;
    }

    /**
     * 判断一个字符串是否是运算符
     * @param str
     * @return 是 返回true， 否则返回 false
     */
    private boolean isOperator(Object str){
        if(str.equals("+") || str.equals("-") || str.equals("*")
                || str.equals("/") || str.equals("#")){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 对两个分数进行计算操作
     * @param num1
     * @param oper
     * @param num2
     * @return
     */
    private static Fraction operation(Fraction num1,String oper, Fraction num2)
            throws  CustomizeException{
        Operation op = new FractionOperation();
        Fraction re = null;
        switch (oper.charAt(0)){
            case '+' :
                re =  (Fraction)op.add(num1,num2);
                break;
            case '-':
                re = (Fraction)op.substract(num1,num2);
                break;
            case '*':
                re = (Fraction)op.multiply(num1,num2);
                break;
            case '/' :
                re = (Fraction)op.divide(num1,num2);
                break;
        }
        return re;
    }


}
