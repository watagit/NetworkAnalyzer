package application;
import java.awt.Color;
import java.awt.GridLayout;

//package guipractice;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class PacketFlow extends JFrame{
	Action flow=new Action();
	Action stat=new Action();
	
//	public void Paint_flow() {
		
//		PacketFlow frame_p = new PacketFlow();
		
//		frame_p.setBounds(10, 10, 440, 400);
//		frame_p.setTitle("パケットの流れ");
//		frame_p.setVisible(true);
//		frame_p.setResizable(false);	
//		frame_p.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//閉じるボタン無効化
//	}
	
	PacketFlow(Object element1, Object element2, Object element3, Object element4, Object element5, Object element6, Object element7){
		
		setBounds(10, 10, 440, 400);
		setTitle("パケットの流れ");
		setVisible(true);
		setResizable(false);	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//閉じるボタン無効化
		
		StringBuffer buf = new StringBuffer();
		buf.append(element1);
		buf.append("\n");
		buf.append(element2);
		buf.append(" ");
		buf.append(element3);
		buf.append(" ");
		buf.append(element4);
		buf.append(" ");
		buf.append(element5);
		buf.append(" ");
		buf.append(element6);
		buf.append(" ");
		buf.append(element7);
		String hello = buf.toString();
		
		JLabel label1 = new JLabel(hello);
		JLabel label2 = new JLabel("パケットの詳細");
		
		label1.setHorizontalAlignment(JLabel.CENTER);
		label2.setHorizontalAlignment(JLabel.CENTER);
		
		LineBorder border = new LineBorder(Color.BLACK, 1, true);
		
		label1.setBorder(border);
		label2.setBorder(border);
		
		JPanel p = new JPanel();
		
		p.setLayout(new GridLayout(2, 2));
		
		p.add(label1);
		p.add(label2);
		
		getContentPane().add(p);
		
	}
	
}
