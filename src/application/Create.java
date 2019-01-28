package application;
import java.io.BufferedReader;
//import java.net.Inet6Address;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;

public class Create {
	static String preStr="";
	public static void analyze() {
		System.out.println("analyzer");
		BufferedReader br=Func.getProcess();
		if(br==null)System.out.println("null exe");
		else System.out.println("not null");
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
		try {
			while(true) {
				
				String str="";
				boolean flag;
//				System.out.println("pre : "+preStr);
				if(!preStr.equals("")) {
					str=preStr;
					preStr="";
//					System.out.println("preStrrrrrrrrrrrrrrrrrr\n "+preStr);
					flag=true;
				}
				else flag=(str=br.readLine())!=null;
				if(flag) {
//					System.out.println("get");
					String sss;
					Date date=new Date();
					String[] sstr=str.split(" ",0);
//					System.out.println("split");
//					System.out.println("str[0] :"+sstr[0]);
					if((sss=sstr[0].substring(0,10)).equals(sdf1.format(date))) {
//						System.out.println("date");
						int year=Integer.parseInt(sstr[0].substring(0,4));
						int month=Integer.parseInt(sstr[0].substring(5,7));
						int day=Integer.parseInt(sstr[0].substring(8,10));
						int hour=Integer.parseInt(sstr[1].substring(0,2));
						int minutes=Integer.parseInt(sstr[1].substring(3,5));
						int second=Integer.parseInt(sstr[1].substring(6,8));
						int millis=Integer.parseInt(sstr[1].substring(9,15));
						Date d= Date.from(LocalDateTime.of(year,month,day,hour,minutes,second).toInstant(ZoneOffset.of("+9")));

						String protocol=sstr[2].replace(",", "");
						if(protocol.equals("IP")){
//							System.out.println("IP");
							InetAddress Fadr;
							InetAddress Tadr;
							String fadr1=sstr[3];
							String[] fadr2=fadr1.split("\\.",5);
							int[] fadr3=new int[4];
							byte[] fadr4=new byte[4];
							for(int r=0;r<4;r++) {
								fadr3[r]=Integer.valueOf(fadr2[r]);
								fadr4[r]=(byte)fadr3[r];
							}
//							System.out.println("Inet");
							Fadr=InetAddress.getByAddress((byte[])fadr4);
							String tadr1=sstr[5];
							String[] tadr2=tadr1.split("\\.",5);
							int[] tadr3=new int[tadr2.length];
							byte[] tadr4=new byte[tadr3.length-1];
							for(int p=0;p<tadr2.length-1;p++) {
								tadr3[p]=Integer.valueOf(tadr2[p]);
								tadr4[p]=(byte)tadr3[p];
							}
							Tadr=InetAddress.getByAddress(tadr4);
							
							IPPacket p=new IPPacket();
							p.d=d;
							p.millis=millis;
							p.protocol=protocol;
							p.from=Fadr;
							p.to=Tadr;
							Bin b=getContent(br);
							p.contents=b.contents;
							p.trans=b.trans;
							p.intro();
//							write(p);
							//Latest edit;
						}

						else if(protocol.matches("[a-z0-9][a-z0-9]:.*")) {
							ARPPacket p=new ARPPacket();
							p.first=protocol;
							p.second=sstr[4].replace(",", "");
							p.d=d;
							p.millis=millis;
							p.status=sstr[6];
							
							Bin b=getContent(br);
							p.contents=b.contents;
							p.trans=b.trans;
							p.intro();
						}
						else if(protocol.equals("ARP")){
							ARPPacket p=new ARPPacket();
							if(sstr[3].trim().equals("Request")) {
								p.first=sstr[5];
								if(sstr[6].replaceAll("[()]","").matches("[a-z0-9][a-z0-9]:.*")) {
									p.second=sstr[8];
								}
								else {
									p.second=sstr[7];
								}

								p.d=d;
								p.millis=millis;
								p.status="request";
								Bin b=getContent(br);
								p.contents=b.contents;
								p.trans=b.trans;
								p.intro();
							}
							else if(sstr[3].equals("Reply")) {
								p.first=sstr[4];
								p.second=sstr[6];
								p.d=d;
								p.millis=millis;
								p.status="Reply";
								
								Bin b=getContent(br);
								p.contents=b.contents;
								p.trans=b.trans;
								p.intro();
							}
							else {
								System.out.println("dumped");
								System.exit(-1);
							}
						}
						else if(protocol.equals("IP6")) {
//							System.out.println("IP6");
							InetAddress Fadr;
							InetAddress Tadr;
							String fadr1=sstr[3];
							String tadr1=sstr[5];
//							System.out.println("Inet");
//							System.out.println("from "+fadr1.substring(20));
//							System.out.println("to "+tadr1.substring(20));
							String fadr2[] =fadr1.split(":", 0);
							for (String string : fadr2) {
//								System.out.println("spli "+string);
							}
							Fadr=InetAddress.getByName(fadr2[0]+"::"+fadr2[2]+":"+fadr2[3]+":"+fadr2[4]);
							String tadr2[] = tadr1.split(":",0);
							
							for (String string : tadr2) {
//								System.out.println("spli "+string);
							}			
							Tadr=InetAddress.getByName(tadr2[0]+"::");				
//							System.out.println("test "+Fadr.getHostName());


//							System.out.println("test "+Tadr.getHostName());
							
							IPPacket p=new IPPacket();
							p.d=d;
							p.millis=millis;
							p.protocol=protocol;
							p.from=Fadr;
							p.to=Tadr;
							Bin b=getContent(br);
							p.contents=b.contents;
							p.trans=b.trans;
							p.intro();
//							write(p);
							//Latest edit;
						}
						else if(protocol.equals("EAP")) {
//							System.out.println("EAP");
							EAPPacket p=new EAPPacket();
							p.d=d;
							p.millis=millis;
							p.protocol=protocol;
							Bin b=getContent(br);
							p.contents=b.contents;
							p.trans=b.trans;
							p.intro();
						}
						else if(protocol.equals("NBF")) {
//							System.out.println("NBF");
							NBFPacket p=new NBFPacket();
							p.d=d;
							p.millis=millis;
							p.protocol=protocol;
							Bin b=getContent(br);
							p.contents=b.contents;
							p.trans=b.trans;
							p.intro();
						}
						else if(protocol.equals("LLDP")) {
//							System.out.println("LLDP");
							LLDPPacket p=new LLDPPacket();
							p.d=d;
							p.millis=millis;
							p.protocol=protocol;
							Bin b=getContent(br);
							p.contents=b.contents;
							p.trans=b.trans;
							p.intro();
						}
						else {
							System.out.println(protocol+ "error");
							System.exit(-1);
						}
					}
				}
				//else //System.out.print("no str");
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	static Bin getContent(BufferedReader br){
		ArrayList<ArrayList<String>> contents=new ArrayList<ArrayList<String>>();
		ArrayList<String> tran=new ArrayList<String>();
		int a=0;
		Bin b=new Bin();
		try{
			while(true) {
				String content=br.readLine();
				if(content!=null) {
//					System.out.println("split");
					String[] cstr=content.split(" ",0);
					if(cstr[0].trim().startsWith("0x")) {
//						System.out.println("Line:"+cstr[0].trim());
						contents.add(new ArrayList<String>());
						for (int i=2;i<cstr.length-1;i++) {
							if(!cstr[i].trim().equals("")) {
//								System.out.println(cstr[i].trim());
								contents.get(a).add(cstr[i].trim());
							}
						}
						a++;
//						System.out.println("traa "+cstr[cstr.length-1]);
						tran.add(cstr[cstr.length-1]);
//						System.out.println("null check");
					}
					else {
//						System.out.println("else "+cstr[0].trim());
						preStr=content;
//						System.out.println("get pre "+preStr);
						b.contents=contents;
						b.trans=tran;
						break;
					}
				}
				else {
					System.out.println("str null");
					if(br.readLine()==null)
						System.exit(-1);
				}
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
			
		return b;
	}
}
