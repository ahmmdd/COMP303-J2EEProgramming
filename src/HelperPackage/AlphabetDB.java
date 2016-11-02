package HelperPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlphabetDB {

	public static void initializeAlphabet() throws Exception{
		//1:Register with the driverManager
		String driverName = "com.mysql.jdbc.Driver";
		Class.forName(driverName).newInstance();
		
		//2:Establishing the connection to the DB Lab1_1 from the DriverManager
		String conURL="jdbc:mysql://localhost:3306/subcomp?useSSL=false";//URL specifying JDBC Connection
		String user ="root";
		String password = "3415";
		Connection con = DriverManager.getConnection(conURL, user, password);
		
		//3: Communicate with the FirstDatabase DB
		//3.1 Creation of a Statement Object 
		Statement stm = (Statement) con.createStatement();
		
		//3.2: Executing of SQL Statement (SQL query)
		
		PreparedStatement pst;
		pst = con.prepareStatement("Update Alphabet Set  Order_position = null, Occurrence= null, Frequency = null, Huffman_code=null;");
		pst.executeUpdate();
		
		//4:Close Everything
		stm.close();
		pst.close();
		con.close();
		
	}
	
	public static void UpdateAlphabet(Alphabet a) throws Exception{
		//1:Register with the driverManager
		String driverName = "com.mysql.jdbc.Driver";
		Class.forName(driverName).newInstance();
		
		//2:Establishing the connection to the DB Lab1_1 from the DriverManager
		String conURL="jdbc:mysql://localhost:3306/subcomp?useSSL=false";//URL specifying JDBC Connection
		String user ="root";
		String password = "3415";
		Connection con = DriverManager.getConnection(conURL, user, password);
		
		//3: Communicate with the FirstDatabase DB
		//3.1 Creation of a Statement Object 
		Statement stm = (Statement) con.createStatement();
		
		//3.2: Executing of SQL Statement (SQL query)
		
		PreparedStatement pst;
		pst = con.prepareStatement("Update Alphabet Set  Order_position = ?, Occurrence= ?, Frequency = ?, Huffman_code=? "
									+ "where Letter=? ;");
		pst.setInt(1, a.getOrder());
		pst.setInt(2, a.getOccurrence());
		pst.setFloat(3, a.getFrequency());
		pst.setString(4, a.getHuffmanCode());
		pst.setString(5, String.valueOf(a.getLetter()));
		
		pst.executeUpdate();
		
		//4:Close Everything
		stm.close();
		pst.close();
		con.close();
		
	}
	
	public static List<Alphabet> GetHuffmanTable() throws Exception{
		//1:Register with the driverManager
		String driverName = "com.mysql.jdbc.Driver";
		Class.forName(driverName).newInstance();
		
		//2:Establishing the connection to the DB Lab1_1 from the DriverManager
		String conURL="jdbc:mysql://localhost:3306/subcomp?useSSL=false";//URL specifying JDBC Connection
		String user ="root";
		String password = "3415";
		Connection con = DriverManager.getConnection(conURL, user, password);
		
		//3: Communicate with the FirstDatabase DB
		//3.1 Creation of a Statement Object 
		Statement stm = (Statement) con.createStatement();
		
		//3.2: Executing of SQL Statement (SQL query)
		
		PreparedStatement pst;
		pst = con.prepareStatement("Select * from alphabet");
				
		ResultSet rs = pst.executeQuery();
		
		List<Alphabet> output =new ArrayList<Alphabet>();
		while (rs.next()) {
			   Alphabet letter = new Alphabet();
			   letter.setLetter(rs.getString("letter").charAt(0));
			   letter.setAscii(rs.getString("Ascii_values"));
			   letter.setOccurrence(Integer.parseInt(rs.getString("Occurrence"))); 
			   letter.setFrequency(Float.parseFloat(rs.getString("Frequency"))); 
			   letter.setHuffmanCode(rs.getString("huffman_code"));
			   output.add(letter);
			}
		
		//4:Close Everything
		stm.close();
		pst.close();
		con.close();
		
		return output;
	}
}
