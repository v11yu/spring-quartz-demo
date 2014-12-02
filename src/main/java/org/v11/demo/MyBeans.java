package org.v11.demo;

public class MyBeans {
	static int num = 0;
	String name;
	public MyBeans(String name){
		this.name = name;
	}
	public void printMessage(){
		System.out.println("hello "+name+", quartz run! " +num++);
		try {
			Thread.sleep(1000*5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
