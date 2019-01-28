package application;
import java.net.InetAddress;

public class IP6Packet extends Packet{

	InetAddress from,to;
	void intro() {
//		contents.stream().forEach(L->L.forEach(S->System.out.println(S)));
		System.out.println("intro");
		System.out.println(protocol);
		trans.stream().forEach(S->System.out.println(S));
		System.out.println(d);
		System.out.println("millis "+millis);
		System.out.println(from);
		System.out.println(to);Main_window.Data_Initialize(String.valueOf((Main_window.id++)), d.toString(), 
				from.toString(), to.toString(), protocol, "0", "0");
	}
}
