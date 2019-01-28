package application;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class Filter extends JFrame{
	public static JCheckBox[] ckbox;
	public static int[] plb;
	public static JTextField inputADR1;
	
	int [] Protocol= new int[7] ;
	Action fil =new Action();
	void P_fil() {
		Filter frame_p = new Filter();
		
		frame_p.setBounds(10, 10, 500, 210);
		frame_p.setTitle("フィルタ指定");
		frame_p.setVisible(true);
		frame_p.setResizable(false);	
		frame_p.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//閉じるボタン無効化
		
	}
	Filter(){
		getContentPane().setLayout(new GridLayout(1,2));
		
		inputADR1 = new JTextField(20);

		JPanel pl = new JPanel();
		pl.setLayout(new GridLayout(3, 3));
		
		JPanel pr = new JPanel();
		pr.setLayout(new GridLayout(2, 1));
		JPanel pr1 = new JPanel();
		JPanel pr2 = new JPanel();
		
		ckbox = new JCheckBox[7];
		ckbox[0] =new JCheckBox("IP",true);
		ckbox[1] =new JCheckBox("LLDP",true);
		ckbox[2] =new JCheckBox("ARP",true);
		ckbox[3] =new JCheckBox("NBF",true);
		ckbox[4] =new JCheckBox("EAP",true);
		ckbox[5] =new JCheckBox("IPv6",true);
		ckbox[6] =new JCheckBox("Default",true);
		
		pl.add(ckbox[0]);
		pl.add(ckbox[1]);
		pl.add(ckbox[2]);
		pl.add(ckbox[3]);
		pl.add(ckbox[4]);
		pl.add(ckbox[5]);
		pl.add(ckbox[6]);
		
		JLabel label1=new JLabel("アドレス入力欄");
		JButton button = new JButton("適用");
		button.addActionListener(fil);
		
		pr1.add(label1);
		pr1.add(inputADR1);
		pr2.add(button);
		pr.add(pr1);
		pr.add(pr2);
		
		getContentPane().add(pl);
		getContentPane().add(pr);
	}
	void decision() {
		for(int i=0;i<ckbox.length;i++){
			if(plb[i]==1) {
				Main_window.p=ckbox[i].getText();
			}
			else{

			}
		}
		
	}
}