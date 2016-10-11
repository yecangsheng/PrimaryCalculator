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

	public static Stack<Object> cloneStack(Stack<Object> stack) {
		Stack<Object> RPNStack = (Stack<Object>) stack.clone();
		Stack<Object> anotherStack = new Stack<Object>();
		while(!RPNStack.empty()){
			anotherStack.push(RPNStack.pop());
		}
		return anotherStack;
	}
}
