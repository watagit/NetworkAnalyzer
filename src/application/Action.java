package application;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
public class Action extends JFrame implements ActionListener {
	int s_t=0;
	int re=0;
	public static String ad;
	public int Stop_start() {
		return s_t;
	}
	public int Reload() {
		return re;
	}
	public int Reload_2(){
		re=0;
		return re;
	}
	public void actionPerformed(ActionEvent e) {
		String str=e.getActionCommand();
		/*switch(str) {
		case "フィルタ":
			PacketFlow flow = new PacketFlow();
			flow.Paint_flow();
		
		case "統計機能":
			Statistics stat=new Statistics();
			stat.Paint_stat();
			
		case "終了":
			
		}	*/
		
//		if(str =="統計機能") {
//				SwingUtilities.invokeLater(() -> {
//					try {
//						Statistics.run();
//					} catch (Exception e1) {
	//					e1.printStackTrace();
//					}
//				});
//		}
		if(str=="フィルタ") {
			Filter fil=new Filter();
			fil.P_fil();
		}
		else if(str=="stop"||str=="scroll_stop") {
			if(str=="scroll_stop"){
				s_t=1;
			}
			else if(str=="stop") {
				
			}
		}
		else if(str=="start"||str=="scroll_start") {
			if(str=="scroll_start"){
				s_t=0;
			}
			else if(str=="start") {
				
			}
		}
		else if(str=="reload"){
			re=1;
		}
		else if(str=="reload_2"){
			re=0;
		}else if(str=="適用") {
			ad=Filter.inputADR1.getText();
			System.out.println(Filter.inputADR1.getText());
			Filter.plb=new int[Filter.ckbox.length];
			for(int i=0;i<Filter.ckbox.length;i++) {
				if(Filter.ckbox[i].isSelected()) {
					Filter.plb[i]=1;
					System.out.println(Filter.ckbox[i].getText());
				}
				else {
					Filter.plb[i]=0;
				}
				if(Filter.plb[i]==1) {
					Main_window.pt[i]=Filter.ckbox[i].getText();
				}
				else{

				}
			}
		}
	}
}
