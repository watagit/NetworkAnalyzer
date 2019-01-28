package application;

public class EAPPacket extends Packet{
	void intro() {
//		contents.stream().forEach(L->L.forEach(S->System.out.println(S)));
		System.out.println("intro");
		System.out.println(protocol);
		trans.stream().forEach(S->System.out.println(S));
		System.out.println(d);
		System.out.println("millis "+millis);
		Main_window.Data_Initialize(String.valueOf((Main_window.id++)), d.toString(), 
				"","", protocol, "0", "0");
	}
}
