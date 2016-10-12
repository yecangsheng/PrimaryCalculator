package cn.edu.tju.showmethecode.calculate;

import java.util.Random;
import java.util.Stack;

public class Utils {

	public static int getRandomInteger(int start, int end) {
		Random r = new Random();
		return r.nextInt(end-start) + start;
	}
	
	public static boolean getRandomBoolean() {
		Random r = new Random();
		return r.nextBoolean();
	}

	/**
	 * 按照a/a+b的概率返回true，按照b/a+b的概率返回false
	 * @param a 第一个数占的比例
	 * @param b 第二个数占的比例
	 * @return
	 */
	public static boolean getBooleanByProb(int a, int b) {
		int sum = getRandomInteger(1, a + b + 1);
		if(sum <= a)
			return true;
		else 
			return false;
	}
	
	public static Stack<Object> cloneStack(Stack<Object> stack) {
		Stack<Object> RPNStack = (Stack<Object>) stack.clone();
		Stack<Object> anotherStack = new Stack<Object>();
		while(!RPNStack.empty()){
			anotherStack.push(RPNStack.pop());
		}
		return anotherStack;
	}
}
