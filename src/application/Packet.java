package application;

import java.util.ArrayList;
import java.util.Date;

public class Packet {
	Date d;
	String protocol;
	double millis;
	ArrayList<ArrayList<String>> contents;
	ArrayList<String> trans=new ArrayList<String>();
	
	void intro() {
		System.out.println("intro");
		System.out.println(d);
		System.out.println("millis "+millis);
		System.out.println(protocol);
	}
}
