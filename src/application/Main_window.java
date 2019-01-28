package application;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Main_window extends JFrame{
	//グローバル変数の設定
	static private String[] columnNames = {"No.", "Time", "From", "To", "Protocol", "length","Info"};

		static ArrayList<String[]> data = new ArrayList<String[]>();
		//static ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		static DefaultTableModel model=new DefaultTableModel(columnNames,0);
		JPanel m=new JPanel();
		static JTable table=new JTable(model);
		JScrollPane sp=new JScrollPane(table);
		static JMenuBar Bar= new JMenuBar();
		JMenu[] CMenu=new JMenu[6];
		JMenuItem[][] Item=new JMenuItem[6][4];
		Action flow=new Action();
		Action stat=new Action();
		Action filter=new Action();
		static Action st=new Action();
		//アイコンの出典 http://icooon-mono.com
		String icon_sa="icon/start.png";
		String icon_so="icon/stopping.png";
		String icon_re="icon/reload.png";
		String icon_qu="icon/question.png";
		ImageIcon start_icon=new ImageIcon(this.getClass().getClassLoader().getResource(icon_sa));
		ImageIcon stop_icon=new ImageIcon(this.getClass().getClassLoader().getResource(icon_so));
		ImageIcon reload_icon=new ImageIcon(this.getClass().getClassLoader().getResource(icon_re));
		ImageIcon question =new ImageIcon(this.getClass().getClassLoader().getResource(icon_qu));

		static int id=1;

		static int S_re=0;		//start stop 判定
		static int reload=0;		//reload 判定

		//Filter変数の設定
		public static String pt[];
		/*
		 pとTestformは実際に流れてくる値を入れてみて、、
		 */
		//public static String p=String.valueOf(id);
		//static String Testfrom="192.186.225.0";
		public static int flg=0;

public static void main(String[] args) throws InterruptedException {

		Main_window frame = new Main_window();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(10, 10, 1000, 1000);
		frame.setTitle("ネットワークアナライザ");
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setJMenuBar(Bar);
		Create.analyze();
		for(int k = 0; k < 30; k ++) {
			Data_Initialize(Integer.toString(k + 1), "1", "1", "1", "1", "1", "1");
			Thread.sleep(30);
		}
	}
	static public void Data_Initialize(
			String id_1,
			String date,
			String from,
			String to,
			String Protocol,
			String length,
			String info
			) {

/*
		if(flg==0) {
			data.add(new String[]{id_1,date,from,to,Protocol,length,info});
			model.addRow(data.get(Integer.valueOf(id_1)-1));
		}
		else if(flg==1) {
			 for(int i=0;i<7;i++) {
				if(pt[i].equals(p)&&Action.ad.equals("")) {
					data.add(new String[]{id_1,date,from,to,Protocol,length,info});
					model.addRow(data.get(Integer.valueOf(id_1)-1));
				}
				else if(pt[i].equals(p)&&Action.ad.equals(Testfrom)) {
					System.out.println("sssssssssssssssssssssssss");
					data.add(new String[]{id_1,date,from,to,Protocol,length,info});
					model.addRow(data.get(Integer.valueOf(id_1)-1));
				}
				else if(Action.ad.equals(Testfrom)){
					data.add(new String[]{id_1,date,from,to,Protocol,length,info});
					model.addRow(data.get(Integer.valueOf(id_1)-1));
				}
				else {
					break;
				}
			}
		}
*/
		//フィルターのテスト時は以下コメントアウト
		//ArrayList<String> sub = new ArrayList<String>();
		data.add(new String[]{id_1,date,from,to,Protocol,length,info});
		/*sub.addAll(Arrays.asList(id,date,from,to,Protocol,length,info));
		data.add(sub);*/
		model.addRow(data.get(Integer.valueOf(id_1)-1));
		S_re=st.Stop_start();
		reload=st.Reload();
		if(S_re==0) {
			int rowcount= model.getRowCount();
			table.scrollRectToVisible(table.getCellRect(rowcount,0,true));
		}
		if(reload==1){
			data.clear();
			model.setRowCount(0);
			id=1;
		}
		reload=st.Reload_2();//Action内のリロード変数を 0 に設定
		//まで
	}

	Main_window() {

		//テーブル定義
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setAutoCreateRowSorter(true);
		table.setEnabled(true);
		TableColumn[] column=new TableColumn[7];
		//カラムの幅を設定
		DefaultTableColumnModel columnModel =(DefaultTableColumnModel)table.getColumnModel();
		for(int i=0;i < columnModel.getColumnCount();i++){
			column[i] = columnModel.getColumn(i);
			if(i==0||i==4||i==5){
				column[i].setPreferredWidth(75);
			}else if(i==1||i==2||i==3){
				column[i].setPreferredWidth(200);
			}else
				column[i].setPreferredWidth(300);
		}
		//セルがダブルクリックされたか
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if(me.getClickCount() == 1) {
//					System.out.println("hello");
//					System.out.println(table.getRowHeight());
					System.out.println(table.getSelectedRow() + 1);
//					System.out.println(table.getSelectedColumnCount());
//					System.out.println(table.getSelectionBackground());
					Point pt = me.getPoint();
					int idx = table.rowAtPoint(pt);
					if(idx >= 0) {
						int row = table.convertRowIndexToModel(idx);
						String str = String.format(
						          "%s (%s)", model.getValueAt(row, 0),model.getValueAt(row, 1),
						          model.getValueAt(row, 2),model.getValueAt(row, 3),
						          model.getValueAt(row, 4),model.getValueAt(row, 5),model.getValueAt(row, 6));
						PacketFlow flow = new PacketFlow();
						flow.Paint_flow();
						JOptionPane.showMessageDialog(
						          table, str, "title", JOptionPane.INFORMATION_MESSAGE
						);
					}
				}
			}
		});

		//カラム設定
		//ARP -> Red , TCP -> GREEN , DNS -> BLUE
		//switch (data.get()){

		//}

		//パネル設定
		//m.setLayout(new FlowLayout(FlowLayout.LEFT, -1, 0));
		m.setPreferredSize(new Dimension(900,950));
		m.add(sp);
		//m.add(Bar);
		getContentPane().add(m, BorderLayout.PAGE_START);
		sp.setPreferredSize(new Dimension(900, 950));
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


		//メニュー定義
		CMenu[0]=new JMenu("ツール");
		CMenu[1]=new JMenu("統計機能");
		CMenu[2]=new JMenu("ヘルプ");
		CMenu[3]=new JMenu();
		CMenu[4]=new JMenu();
		CMenu[5]=new JMenu();
		CMenu[2].setIcon(question);
		CMenu[3].setIcon(start_icon);
		CMenu[4].setIcon(stop_icon);
		CMenu[5].setIcon(reload_icon);

		Item[0][0]=new JMenuItem("フィルタ");
		Item[1][0]=new JMenuItem("統計機能");
		Item[2][0]=new JMenuItem("概要");
		Item[2][1]=new JMenuItem("使用方法");
		Item[3][0]=new JMenuItem("start");
		Item[3][1]=new JMenuItem("scroll_start");
		Item[4][0]=new JMenuItem("stop");
		Item[4][1]=new JMenuItem("scroll_stop");
		Item[5][0]=new JMenuItem("reload");

		Item[3][0].addActionListener(st);
		Item[3][1].addActionListener(st);
		Item[4][0].addActionListener(st);
		Item[4][1].addActionListener(st);
		Item[5][0].addActionListener(st);

		Bar.add(CMenu[0]);
		Bar.add(CMenu[1]);
		Bar.add(CMenu[3]);
		Bar.add(CMenu[4]);
		Bar.add(CMenu[5]);
		Bar.add(Box.createHorizontalGlue());//ヘルプを右端に移動
		Bar.add(CMenu[2]);



		CMenu[0].add(Item[0][0]);
		CMenu[1].add(Item[1][0]);
		CMenu[2].add(Item[2][0]);
		CMenu[2].add(Item[2][1]);
		CMenu[3].add(Item[3][0]);
		CMenu[3].add(Item[3][1]);
		CMenu[4].add(Item[4][0]);
		CMenu[4].add(Item[4][1]);
		CMenu[5].add(Item[5][0]);

		//ページ移動
		Item[0][0].addActionListener(filter);
		Item[1][0].addActionListener(stat);
	}
}
