package cn.edu.tju.showmethecode.calculate;

import java.util.Stack;

import sun.rmi.runtime.Log;


public class RPNExpression {
	
	private Stack<Object> RPNExp = new Stack<Object>();	//逆波兰表达式
	private int length;			//运算数个数
	private String value;		//表达式的值
	
	public RPNExpression() {
		this(4);
	}
	
	public RPNExpression(int length) {
		if(length<2)
			throw new IllegalArgumentException("长度设置过短！");
		
		this.length = length;
		generateRPNExp(length);
		calcValue();
	}
	
	private void generateRPNExp(int length) {
		int symbolCount = 0;
		int operatorCount = 0;
		while(operatorCount + symbolCount < length * 2 - 1){
			
			if(operatorCount-1 > symbolCount && operatorCount < length){
				if(Utils.getRandomBoolean()){		//true生成运算符，false操作数
					this.RPNExp.push(generateSymbol());
					symbolCount ++;
				}else{
					this.RPNExp.push(Utils.getRandomBoolean() ? generateNumeric() : generateProperFraction());
					operatorCount ++;
				}
			}else if(operatorCount-1 <= symbolCount && operatorCount < length) {									//true生成整数，false生成分数
				this.RPNExp.push(Utils.getRandomBoolean() ? generateNumeric() : generateProperFraction());
				operatorCount ++;
			}else if(operatorCount-1 > symbolCount && operatorCount >=length) {
				this.RPNExp.push(generateSymbol());
				symbolCount ++;
			}
		}
	}
	
	private int generateNumeric() {
		return Utils.getRandomInteger(1, 100);
	}
	
	private char generateSymbol() {
		char[] symbol = {'+','−','×','÷'};
		return symbol[Utils.getRandomInteger(0, 4)];
	}
	
	private ProperFraction generateProperFraction() {
		int denominator = Utils.getRandomInteger(2, 10);
		int numerator = Utils.getRandomInteger(1, denominator);
		return new ProperFraction(numerator, denominator);
	}
	/*
	 * get逆波兰表达式
	 */
	public static String generateArithmetic(int length){
		return (new RPNExpression(length)).convertToSeqExp();
	}
	
	private void calcValue(){
		Stack<Object> RPNStack = Utils.cloneStack(this.RPNExp);
		Stack<Object> numStack = new Stack<Object>();			//数字栈 
		
		while(!RPNStack.empty()){
			if(RPNStack.peek() instanceof Integer || RPNStack.peek() instanceof ProperFraction){
				numStack.push(RPNStack.pop());
			}else if(RPNStack.peek() instanceof Character){
				Object rightOperator = numStack.pop();
				Object leftOperator = numStack.pop();
				if(rightOperator instanceof Integer)
					rightOperator = new ProperFraction((Integer) rightOperator, 1);
				if(leftOperator instanceof Integer)
					leftOperator = new ProperFraction((Integer) leftOperator, 1);
					
				switch ((Character) RPNStack.pop()) {
				case '+':
					numStack.push(((ProperFraction)leftOperator).add((ProperFraction)rightOperator));
					break;
				case '−':
					numStack.push(((ProperFraction)leftOperator).minus((ProperFraction)rightOperator));
					break;
				case '×':
					numStack.push(((ProperFraction)leftOperator).multiply((ProperFraction)rightOperator));
					break;
				case '÷':
					numStack.push(((ProperFraction)leftOperator).devide((ProperFraction)rightOperator));
					break;
				default:
//					throw new Exception("");
					break;
				}
			}
		}
		
		this.value = ((ProperFraction) numStack.pop()).toString();
	}
	
	private String convertToSeqExp() {
		System.out.println(this.RPNExp.toString());
		Stack<Object> RPNStack = Utils.cloneStack(this.RPNExp);
		Stack<String> tempExpStack = new Stack<String>();		//临时表达式栈
		
		while(!RPNStack.empty()){
			if(RPNStack.peek() instanceof Integer || RPNStack.peek() instanceof ProperFraction){
				tempExpStack.push(RPNStack.pop().toString());
			}else if(RPNStack.peek() instanceof Character){
				String rightOperator = tempExpStack.pop();
				String leftOperator = tempExpStack.pop();
				
				if("×÷−".contains(RPNStack.peek().toString())){
					if(isNeedBrackets((Character) RPNStack.peek(), rightOperator, true)){
						rightOperator = "( " + rightOperator + " )";
					}
					if(isNeedBrackets((Character) RPNStack.peek(), leftOperator, false)){
						leftOperator = "( " + leftOperator + " )";
					}
				}
				
				tempExpStack.push(leftOperator + " " + RPNStack.pop().toString() + " " + rightOperator);
			}
		}
		
		return tempExpStack.pop();
	} 
	
	/*
	 * 判断一个临时表达式是否需要加括号，
	 * 有三种情况：
	 * 1. 当前符号是 ×÷，临时表达式符号是 +−
	 * 2. 当前符号是 −，临时表达式是右操作数，且符号为 +−
	 * 3. 当前符号是 ÷，临时表达式是右操作数，且符号为 ×÷
	 */
	private boolean isNeedBrackets(Character currentSymbol, String tempExp, boolean rightOrNot) {
		//判断括号外是什么符号
		String priority = symbolOutsideOfBranckets(tempExp);
		
		if("+−".equals(priority) && "×÷".contains(currentSymbol.toString())) {
			return true;
		}
		if("×÷".equals(priority) && rightOrNot && "÷".equals(currentSymbol.toString())) {
			return true;
		}
		if("+−".equals(priority) && rightOrNot && "−".equals(currentSymbol.toString())) {
			return true;
		}
			
		return false;
	}
	
	/*
	 * 判断临时操作数最外层的符号是什么，×÷ 或 +− 后者无符号
	 * count是括号的左括号的数量
	 */
	private String symbolOutsideOfBranckets(String tempExp) {
		if(!tempExp.contains(" ")){
			return "nothing";				
		}
		
		String[] elements = tempExp.split(" ");
		int count = 0;
		
		for(int i=0; i<elements.length; i++){
			if(count == 0 && "+−".contains(elements[i])) {
				return "+−";
			}else if("(".equals(elements[i])) {
				count ++;
			}else if(")".equals(elements[i])) {
				count --;
			}
		}
		return "×÷";
	}
	
	public int getLength() {
		return this.length;
	}
	
	public String getSeqExp() {
		return convertToSeqExp();
	}
	
	public String getValue() {
		return this.value;
	}
	
	public String toString() {
		Stack<Object> RPNStack = Utils.cloneStack(this.RPNExp);
		StringBuilder sb = new StringBuilder();
		while(!RPNStack.empty())
			sb.append(RPNStack.pop().toString()).append(" ");
		return sb.toString();
	}
}
