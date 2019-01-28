package application;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Func {
	public static BufferedReader getProcess() {
		try {
			String[] o2=new String[8];
			o2[0]="tcpdump";
			o2[1]="-tttt";
			o2[2]="-X";
			o2[3]="-l";
			o2[4]="-f";
			o2[5]="-n";
			o2[6]="-i"; //intarface = enp2s0
			o2[7]="ens33";
			ProcessBuilder pb=new ProcessBuilder(o2);
			Process p2 = pb.start();
			BufferedReader br = new BufferedReader(new InputStreamReader(p2.getInputStream()));
			
			return br;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
