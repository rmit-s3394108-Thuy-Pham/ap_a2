package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hsqldb.Server;
public class DBConnection {
//	Server hsqlServer = null; 
	Connection connection = null; 
	ResultSet rs = null;
	
	
	public DBConnection() {
		// TODO Auto-generated constructor stub
		try { 
			Class.forName("org.hsqldb.jdbcDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:TestDB", "sa", "123");
			connection.prepareStatement("drop table people if exists;").execute();
			connection.prepareStatement("create table people (name varchar(30) primary key, image varchar(30), status varchar(30), gender varchar(10), age Integer not null, postcode varchar(20) );").execute();
//			connection.prepareStatement("insert into barcodes (id, barcode)"+ "values (1, '12345577');").execute();
			
			 // query from the db
//			rs = connection.prepareStatement("select id, barcode from barcodes").executeQuery();
//			rs.next();
//			System.out.println(String.format("ID: %1d, Name:%1s", rs.getInt(1), rs.getString(2))); 
			connection.commit();
		} catch (SQLException e2) { 
			e2.printStackTrace();
		} catch (ClassNotFoundException e2) { 
			e2.printStackTrace();
		}
	}
	public static void main(String args[]) {
		new DBConnection();
	}
}
