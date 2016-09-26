package test;

import java.util.ArrayList;

public class ListTest {

	static void testAddAll(){
		Object o1 = new Object();
		Object o2 = new Object();
		Object o3 = new Object();
		ArrayList<Object> l1 = new ArrayList<Object>();
		l1.add(o1);
		l1.add(o2);
		ArrayList<Object> l2 = new ArrayList<Object>();
		l2.add(o2);
		l2.add(o3);
		l1.addAll(l2);
		for(Object o: l1){
			System.out.println(o);
		}
	}
	public static void main(String[] args) {
		testAddAll();
	}
}
