package com.question.handler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class TestFile {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//获取文件:一种是字节流，一种是字符流：（reader wirter）
		String str="";
		BufferedReader bufferedReader = new BufferedReader(new FileReader("f:/text.txt"));
		String temp = bufferedReader.readLine();
		while(temp!=null){
			str=temp+" ";
			temp = bufferedReader.readLine();
		}
		String[] arr = str.split("");
		
		HashMap<String,Integer> hm = new HashMap<String, Integer>();
		for(String s:arr){
			if(hm.containsKey(s)){
				int value = hm.get(s);
				hm.put(s, 1+value);
			}else{
				hm.put(s, 1);
			}
		}
		for(String s:hm.keySet()){
			System.out.println(s+"\t"+hm.get(s));
		}
		
		
		bufferedReader.close();
		//读取文件
		//分割单词
		//计数
	}

}
