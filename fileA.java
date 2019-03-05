package com.src;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SingleTonDesignPattern implements Serializable {

	
	private static SingleTonDesignPattern obj = new SingleTonDesignPattern();
	
	private SingleTonDesignPattern(){
		
	}
	
	private static SingleTonDesignPattern methodA(){
		return obj;
	}
	
	private int num =0;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	protected Object readResolve(){
		return obj;
		
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		SingleTonDesignPattern obj=SingleTonDesignPattern.methodA();
		
		String fileName="singleTon.bin";
		saveToFile(obj,fileName);
		System.out.println(obj);
		SingleTonDesignPattern obj1=SingleTonDesignPattern.methodA();
		SingleTonDesignPattern result=readFromFile(obj1,fileName);
		System.out.println(result);

		
		
	}

	private static SingleTonDesignPattern readFromFile(
			SingleTonDesignPattern obj1, String fileName) throws IOException, ClassNotFoundException {

		FileInputStream fin = new FileInputStream(fileName);
		ObjectInputStream in = new ObjectInputStream(fin);
		
		return (SingleTonDesignPattern) in.readObject();
	}

	

	private static void saveToFile(SingleTonDesignPattern obj, String fileName) throws IOException {

		FileOutputStream fout = new FileOutputStream(fileName);
		ObjectOutputStream out = new ObjectOutputStream(fout);
		out.writeObject(obj);
	}

}
