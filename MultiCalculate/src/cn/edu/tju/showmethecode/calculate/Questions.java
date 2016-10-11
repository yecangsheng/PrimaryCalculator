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
		
		writeToTxt(generateQuestions(100));
		
	}
	
	/*
	 * 初始化n道题，存到map中
	 */
	public static HashMap<String, String> generateQuestions(int num) {
		HashMap<String, String> questionsMap = new HashMap<String, String>();
		for(int i=0; i<num; i++) {
			RPNExpression  newRPNExp = new RPNExpression(Utils.getRandomInteger(2, 5));
			questionsMap.put(newRPNExp.getSeqExp(), newRPNExp.getValue());
		}
		return questionsMap;
	}

	/*
	 * 将初始化的n道题从map写入文件
	 */
	public static boolean writeToTxt(HashMap<String, String> questionsMap) {
		try {
			BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("tempfile//questions.txt")));
			
			for(Map.Entry<String, String> entry : questionsMap.entrySet()){
				bufw.write(entry.getKey() + " = " + entry.getValue());
				bufw.newLine();
				bufw.flush();
			}
			
			bufw.close();
			
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
