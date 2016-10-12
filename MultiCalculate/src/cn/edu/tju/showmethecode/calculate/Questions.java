package cn.edu.tju.showmethecode.calculate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;





public class Questions {

	public Questions() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		RPNExpression  re = new RPNExpression(2);
//		System.out.println(re.toString());
//		System.out.println(RPNExpression.generateArithmetic(4));
//		System.out.println(re.getSeqExp());
//		System.out.println(re.getValue());
		
		writeToTxt(generateQuestions(100000));
		
	}
	
	/*
	 * 初始化n道题，存到map中
	 */
	public static HashMap<String, Integer> generateQuestions(int num) {
		HashMap<String, Integer> questionsMap = new HashMap<String, Integer>();
		for(int i=0; i<num; i++) {
			RPNExpression  newRPNExp = new RPNExpression(Utils.getRandomInteger(2, 6));
			questionsMap.put(newRPNExp.getSeqExp() + " = " + newRPNExp.getValue(), newRPNExp.getLevel());
		}
		return questionsMap;
	}

	/*
	 * 将初始化的n道题从map写入文件
	 */
	public static boolean writeToTxt(HashMap<String, Integer> questionsMap) {
		try {
			BufferedWriter bufw1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("tempfile//1.txt")));
			BufferedWriter bufw2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("tempfile//2.txt")));
			BufferedWriter bufw3 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("tempfile//3.txt")));
			BufferedWriter bufw4 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("tempfile//4.txt")));
			BufferedWriter bufw5 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("tempfile//error.txt")));
			
			for(Map.Entry<String, Integer> entry : questionsMap.entrySet()){
				switch (entry.getValue()) {
				case 1:
					bufw1.write(entry.getKey());
					bufw1.newLine();
					bufw1.flush();
					break;
				case 2:
					bufw2.write(entry.getKey());
					bufw2.newLine();
					bufw2.flush();
					break;
				case 3:
					bufw3.write(entry.getKey());
					bufw3.newLine();
					bufw3.flush();
					break;
				case 4:
					bufw4.write(entry.getKey());
					bufw4.newLine();
					bufw4.flush();
					break;
				default:
					bufw5.write(entry.getKey());
					bufw5.newLine();
					bufw5.flush();
					break;
				}
				
			}
			
			bufw1.close();
			bufw2.close();
			bufw3.close();
			bufw4.close();
			bufw5.close();
			
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
