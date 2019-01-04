package cn.edu.tju.showmethecode.calculate;

import java.util.Stack;

import com.sun.accessibility.internal.resources.accessibility;

import sun.rmi.runtime.Log;
import sun.security.util.Length;


public class RPNExpression {
	
	private Stack<Object> RPNExp = new Stack<Object>();	//逆波兰表达式
	private int length;			//运算数个数
	private String value;		//表达式的值
	private int level;			//该表达式等级
	
	public RPNExpression() {
		this(4);
	}
	
	public RPNExpression(int length) {
		if(length<2)
			throw new IllegalArgumentException("长度设置过短！");
		
		if(length == 5) {
			this.level = 4;
		} else if(length == 4 || length == 3) {
			this.level = 2;
		} else if(length == 2) {
			this.level = 1;
		} else {
			this.level = 5;
		}
		
		this.length = length;
		generateRPNExp(length);
		if(getLevel() != 5) {
			calcValue();
		} else {
			this.value = "###";
		}
		
	}
	
	/**
	 * 依据长度生成表达式
	 * @param length
	 */
	private void generateRPNExp(int length) {
		int symbolCount = 0;
		int operatorCount = 0;
		while(operatorCount + symbolCount < length * 2 - 1){
			
			if(operatorCount-1 > symbolCount && operatorCount < length){
				//true生成运算符，false操作数
				if(Utils.getRandomBoolean()){		
					this.RPNExp.push(generateSymbol(length));
					symbolCount ++;
				}else{
					//true生成整数，false生成分数
					this.RPNExp.push(GenerateOperator(length) ? generateNumeric(length) : generateProperFraction());
					operatorCount ++;
				}
			}else if(operatorCount-1 <= symbolCount && operatorCount < length) {									
				//true生成整数，false生成分数
				this.RPNExp.push(GenerateOperator(length) ? generateNumeric(length) : generateProperFraction());
				operatorCount ++;
			}else if(operatorCount-1 > symbolCount && operatorCount >=length) {
				this.RPNExp.push(generateSymbol(length));
				symbolCount ++;
			}
		}
	}
	
	/**
	 * 生成整数还是分数，根据表达式长度以不同的概率生成
	 * true生成整数，false生成分数
	 * 长度为2，只生成整数；长度为3，整数:分数=11：1；长度为4，整数:分数=5：1；长度为5，整数：分数=2：1
	 */
	private boolean GenerateOperator(int length) {
		if(length == 2) {
			return true;
		} else if(length == 3 && Utils.getBooleanByProb(11, 1)) {
			return true;
		} else if(length == 4 && Utils.getBooleanByProb(5, 1)) {
			return true;
		} else if(length == 5 && Utils.getBooleanByProb(2, 1)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 生成100内正整数
	 * @return
	 */
	private int generateNumeric(int length) {
		if(length == 2){
			return Utils.getRandomInteger(1, 100);
		} else if(length == 3) {
			return Utils.getRandomInteger(1, 60);
		} else if(length == 4) {
			return Utils.getRandomInteger(1, 40);
		} else {
			return Utils.getRandomInteger(1, 20);
		}
	}
	
	/**
	 * 生成符号,长度为2或3的时候，按照制定比例生成。长度大于3等比例生成
	 * @return
	 */
	private char generateSymbol(int length) {
		char[] symbol = {'+','−','×','÷'};
		if(length == 2 && Utils.getBooleanByProb(5, 1)) {
			return symbol[Utils.getRandomInteger(0, 2)];
		} else if(length == 3 && Utils.getBooleanByProb(3, 1)) {
			return symbol[Utils.getRandomInteger(0, 2)];
		} else {
			return symbol[Utils.getRandomInteger(0, 4)];
		}
	}
	
	/**
	 * 生成真分数
	 * @return
	 */
	private ProperFraction generateProperFraction() {
		int denominator = Utils.getRandomInteger(2, 10);
		int numerator = Utils.getRandomInteger(1, denominator);
		return new ProperFraction(numerator, denominator);
	}
	
	/**
	 * get中序表达式
	 */
	public static String generateArithmetic(int length){
		return (new RPNExpression(length)).convertToSeqExp();
	}
	
	/**
	 * 计算表达式值
	 */
	private void calcValue(){
		Stack<Object> RPNStack = Utils.cloneStack(this.RPNExp);
		Stack<Object> numStack = new Stack<Object>();			//数字栈 
		
		while(getLevel() != 5 && !RPNStack.empty()){
			if(RPNStack.peek() instanceof Integer || RPNStack.peek() instanceof ProperFraction){
				numStack.push(RPNStack.pop());
			}else if(RPNStack.peek() instanceof Character){
				Object rightOperator = numStack.pop();
				Object leftOperator = numStack.pop();
				if(rightOperator instanceof Integer)
					rightOperator = new ProperFraction((Integer) rightOperator, 1);
				if(leftOperator instanceof Integer)
					leftOperator = new ProperFraction((Integer) leftOperator, 1);
				
				ProperFraction left = (ProperFraction) leftOperator;
				ProperFraction right = (ProperFraction) rightOperator;
				
				//判断计算过程中是否有负数或者越界数出现，设定为5等，有真分数出现,设定4等
				if(left.isNegativeNum() || left.isOutOfBound() || right.isNegativeNum() || right.isOutOfBound()) {
					this.level = getLevel() < 5 ? 5 : getLevel();
				} else if(left.isFraction() || right.isFraction()) {
					this.level = getLevel() < 4 ? 4 : getLevel();
				}
					
				switch ((Character) RPNStack.pop()) {
				case '+':
					numStack.push(left.add(right));
					break;
				case '−':
					numStack.push(left.minus(right));
					break;
				case '×':
					numStack.push(left.multiply(right));
					break;
				case '÷':
					if(!right.isNormalFraction()) {
						this.level = 5;
						break;
					}
					numStack.push(left.devide(right));
					break;
				default:
//					throw new Exception("");
					break;
				}
			}
		}
		
		if(getLevel() != 5) {
			//判断结果数是否是负数，越界数，分数
			if(((ProperFraction)numStack.peek()).isNegativeNum() || ((ProperFraction)numStack.peek()).isOutOfBound()) {
				this.level =  5;
			} else if(((ProperFraction)numStack.peek()).isFraction()) {
				this.level = getLevel() < 4 ? 4 : getLevel();
			}
			
			this.value = ((ProperFraction) numStack.pop()).toString();
		} else {
			this.value = "-1";
		}
	}
	
	/**
	 * 转换逆波兰表达式到中序表达式
	 * @return
	 */
	private String convertToSeqExp() {
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
		
		//如果最后得到的表达式中包含'()'，那么根据长度定等级是3或4,没有'()'有'×÷'是2等
		if(tempExpStack.peek().contains("(")) {
			if(getLength() <= 4) {
				this.level = getLevel() < 3 ? 3 : getLevel();
			} else if(getLength() == 5) {
				this.level = getLevel() < 4 ? 4 : getLevel();
			}
		} else if(tempExpStack.peek().contains("×") || tempExpStack.peek().contains("÷")) {
			this.level = getLevel() < 2 ? 2 : getLevel();
		}
		
		System.out.println(this.RPNExp.toString() + "======" + tempExpStack.toString() + "------" + getLevel() + "=====" + getLength());
		return tempExpStack.pop();
	} 
	
	/**
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
	
	/**
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
	
	/**
	 * 得到表达式长度
	 * @return
	 */
	public int getLength() {
		return this.length;
	}
	
	/**
	 * 得到表达式等级
	 */
	public int getLevel() {
		return this.level;
	}
	
	/**
	 * 得到中序表达式
	 * @return
	 */
	public String getSeqExp() {
		return convertToSeqExp();
	}
	
	/**
	 * 得到表达式的值
	 * @return
	 */
	public String getValue() {
		return this.value;
	}
	
	/**
	 * 逆波兰表达式形式展示
	 */
	public String toString() {
		Stack<Object> RPNStack = Utils.cloneStack(this.RPNExp);
		StringBuilder sb = new StringBuilder();
		while(!RPNStack.empty())
			sb.append(RPNStack.pop().toString()).append(" ");
		return sb.toString();
	}
}
