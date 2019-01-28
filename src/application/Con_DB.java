package application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Con_DB {
	Connection conn;
	java.sql.Statement stmt;
	Statement sta;
	
	String sql;
	ResultSet rs;
	public void Open(){//DBhenosetuzoku
		try {
		conn=null;
		
			conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/DB_Con", 
					"root", "NETWORK1@root");						//windows name root@localhost pz NETWORK@1root
			stmt =conn.createStatement();
			sta=conn.createStatement();
		}catch(SQLException e) {
			System.out.println("*****connerr*****");
		// catch(Exception e) {
			//System.out.println("*****connerr*****");
			e.printStackTrace();
		}
	}
	public void Sql_In(int id,String to,String protocol,String from) throws Exception{//table he ataiwo ireruyo
		sql="insert into department(Id,Packet_From,Packet_To,Packet_Protocol) values("+id+",'"+from+"','"+to+"','"+protocol+"')";
		/*sql colunm id dest from protocol*/
		int num=sta.executeUpdate(sql);
		System.out.println("kousinsaretagyou="+num);
		
	}
	public void Sql_Out()throws Exception { //table kara ataiwo toridasuyo
		/*Packet instance printout*/
		String[] to=new String[100];
		String[] id=new String[100];
		String[] from=new String[100];
		String[] protocol=new String[100];
		sql="select Id,Packet_To, Packet_From ,Packet_Protocol from department";
		rs=stmt.executeQuery(sql);
		int i=0;
		while(rs.next()) {
			System.out.println(rs.getString("Id"));
			System.out.println(rs.getString("Packet_To"));
			System.out.println(rs.getString("Packet_From"));
			System.out.println(rs.getString("Packet_Protocol"));
			to[i]=rs.getString("Packet_To");
			id[i]=rs.getString("Id");
			from[i]=rs.getString("Packet_From");
			protocol[i]=rs.getString("Packet_Protocol");
			i++;
		}
	}
	public void Fin() {//DB no connection wo kiruyo
		try {
            if (null != conn) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
}
