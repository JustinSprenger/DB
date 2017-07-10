package database;
import java.sql.*;
import java.util.Scanner;

import javax.print.DocFlavor.CHAR_ARRAY;
public class Database {
	private String user;
	private String pass;
	private String address;
	private int port;
	private String database;
	private int id;
	Connection conn;
	/**
	 * 
	 * @param add -url
	 * @param port -portnumber (3306 for mysql)
	 * @param db -database name
	 * @param user -admin user
	 * @param pass -password
	 */
	public Database(String add,int port,String db,String user,String pass){
		this.user = user;
		this.pass = pass;	
		this.address = add;
		this.port = port;
		this.database = db;
		//jdbc:mysql://localhost/db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getDatabase(){
		try {
			
			conn = DriverManager.getConnection("jdbc:postgresql://"+address+":"+port+"/"+database+"?"+"user=" + user +"&"+"password=" + pass);
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from adresse"); 
			while(rs.next()){
				//System.out.println(rs.getString("buchungs_nr")+"  "+rs.getFloat("preis")+"  "+rs.getInt("menge")+"  "+rs.getInt("f_kunden_nr")+"  "+rs.getString("f_name")+"  "+rs.getString("f_name"));
				System.out.println(rs.getString("Straﬂe")+"  "+rs.getString("Hausnummer")+"  "+rs.getString("Ort")+"  "+rs.getString("Postleitzahl"));
			}
				conn.close();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	public String getDatabasewn(){
		try {
			int i = 1;
			conn = DriverManager.getConnection("jdbc:postgresql://"+address+":"+port+"/"+database+"?"+"user=" + user +"&"+"password=" + pass);
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from adresse"); 
			while(rs.next()){
				//System.out.println(rs.getString("buchungs_nr")+"  "+rs.getFloat("preis")+"  "+rs.getInt("menge")+"  "+rs.getInt("f_kunden_nr")+"  "+rs.getString("f_name")+"  "+rs.getString("f_name"));
				System.out.println("Zeile " + i +": "+ rs.getString("Straﬂe")+"  "+rs.getString("Hausnummer")+"  "+rs.getString("Ort")+"  "+rs.getString("Postleitzahl"));
				i++;
			}
				conn.close();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	public String[] getDatabasefromnum(int i){
		String[] val = new String[4];
		try {
			
			conn = DriverManager.getConnection("jdbc:postgresql://"+address+":"+port+"/"+database+"?"+"user=" + user +"&"+"password=" + pass);
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from adresse"); 
			
			for(int j = 1;j<=i;j++){
				rs.next();
				//System.out.println(rs.getString("buchungs_nr")+"  "+rs.getFloat("preis")+"  "+rs.getInt("menge")+"  "+rs.getInt("f_kunden_nr")+"  "+rs.getString("f_name")+"  "+rs.getString("f_name"));
				//System.out.println(rs.getString("Straﬂe")+"  "+rs.getString("Hausnummer")+"  "+rs.getString("Ort")+"  "+rs.getString("Postleitzahl"));
			}
			val[0] = rs.getString("Straﬂe");
			val[1] = rs.getString("Hausnummer");
			val[2] = rs.getString("Ort");
			val[3] = rs.getString("Postleitzahl");
			
				conn.close();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return val;
	}
	public String row(){
		Scanner sc = new Scanner(System.in);
		try {
			char s = ' ';
			
			conn = DriverManager.getConnection("jdbc:postgresql://"+address+":"+port+"/"+database+"?"+"user=" + user +"&"+"password=" + pass);
			Statement stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs=stmt.executeQuery("select * from adresse"); 
			
			do{
				System.out.println("next = n");
				System.out.println("previos = p");
				System.out.println("quit = q");
			
				s=sc.next().charAt(0);
				System.out.println(s);
				
				switch (s) {
					case 'n':
						if(rs.isLast()||rs.isAfterLast()){
							rs.first();
						}else{
							rs.next();
						}
						//System.out.println(rs.getString("buchungs_nr")+"  "+rs.getFloat("preis")+"  "+rs.getInt("menge")+"  "+rs.getInt("f_kunden_nr")+"  "+rs.getString("f_name")+"  "+rs.getString("f_name"));
						System.out.println(rs.getString("Straﬂe")+"  "+rs.getString("Hausnummer")+"  "+rs.getString("Ort")+"  "+rs.getString("Postleitzahl"));
						break;
					case 'p':
						if(rs.isFirst()||rs.isBeforeFirst()){
							rs.last();
						}else{
							rs.previous();
						}
						//System.out.println(rs.getString("buchungs_nr")+"  "+rs.getFloat("preis")+"  "+rs.getInt("menge")+"  "+rs.getInt("f_kunden_nr")+"  "+rs.getString("f_name")+"  "+rs.getString("f_name"));
						System.out.println(rs.getString("Straﬂe")+"  "+rs.getString("Hausnummer")+"  "+rs.getString("Ort")+"  "+rs.getString("Postleitzahl"));
						break;
					case 'q':
				
						break;

					default:
						break;
				}
			}while(s!='q');
			
				conn.close();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	public void insertDatabase(String[] values){
		String val="'";
		for(int i =0;i<values.length;i++){
			if(i==values.length-1){
				val = val + values[i] + "'";
			}else{
				val = val + values[i] + "','";
			}
		}
		System.out.println(val);
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://"+address+":"+port+"/"+database+"?"+"user=" + user +"&"+"password=" + pass);
			Statement stmt=conn.createStatement();
			/**
			id = id +1;
			val = "'"+id +"',"+ val;
			System.out.println(val);
			**/
			stmt.execute("INSERT INTO adresse VALUES ("+val+")");
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	public void updateDatabase(String[] values){

		try {
			conn = DriverManager.getConnection("jdbc:postgresql://"+address+":"+port+"/"+database+"?"+"user=" + user +"&"+"password=" + pass);
			Statement stmt=conn.createStatement();
			/**
			 id = id +1;
			 val = "'"+id +"',"+ val;
			 System.out.println(val);
			 **/
			ResultSet rs=stmt.executeQuery("select * from adresse Where Straﬂe = '" + values[0] + "' AND Hausnummer = '" + values[1]+"' AND Ort = '" + values[2]+"' AND Postleitzahl = '" + values[3]+"'"); 
			rs.next();
			System.out.println(rs.getString("Straﬂe")+"  "+rs.getString("Hausnummer")+"  "+rs.getString("Ort")+"  "+rs.getString("Postleitzahl"));
			stmt.executeUpdate("DELETE FROM adresse WHERE Straﬂe = '" + values[0] + "' AND Hausnummer = '" + values[1]+"' AND Ort = '" + values[2]+"' AND Postleitzahl = '" + values[3]+"'");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
