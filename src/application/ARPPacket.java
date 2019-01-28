package application;

public class ARPPacket extends Packet{
	String first,second;
	String status;
	void intro() {
//		contents.stream().forEach(L->L.forEach(S->System.out.println(S)));
		System.out.println("intro");
		System.out.println("ARP "+status);
		trans.stream().forEach(S->System.out.println(S));
		System.out.println(d);
		System.out.println("millis "+millis);
		System.out.println(first);
		System.out.println(second);
		Main_window.Data_Initialize(String.valueOf((Main_window.id++)), d.toString(), 
				first.toString(), second.toString(), "ARP "+status, "0", "0");
	}
}
enum ARPS{
	requet,query,reply
}
