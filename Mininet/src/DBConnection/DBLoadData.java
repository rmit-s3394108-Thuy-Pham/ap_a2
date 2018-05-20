package DBConnection;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Adult;
import Model.Children;
import Model.Profile;
import Model.ReadFile;
import Model.YoungChild;

public class DBLoadData {
	DBConnection dbConnection = new DBConnection();
	ReadFile readFile; 
	ResultSet rs = null;
	
	public DBLoadData() {
		String strData;
		String strName;
		String strImage;
		String strStatus;
		String strGender;
		int age;
		String strPostcode;
		try {
			readFile = new ReadFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Profile pro: readFile.getListofPros()) {
			strName = pro.getName();
			strImage = pro.getImage();
			strStatus = pro.getStatus();
			strGender = pro.getGender();
			strPostcode = pro.getPostcode();
			if(pro instanceof Adult) {
				age = ((Adult) pro).getAge();
				strData = String.format("'%s'" +","+"'%s'"+","+"'%s'"+","+ "'%s'"+","+"'%d'"+ ","+"'%s'", strName,strImage,strStatus, strGender, age,strPostcode);
				
				try {
				dbConnection.connection.prepareStatement("insert into people values (" + strData+");").execute();
				}catch(SQLException e) {
					System.out.println("DBLoadData: Error to load Adult!");
				}
			}
			if(pro instanceof Children) {
				age = ((Children) pro).getAge();
				strData = String.format("'%s'" +","+"'%s'"+","+"'%s'"+","+ "'%s'"+","+"'%d'"+ ","+"'%s'", strName,strImage,strStatus, strGender, age,strPostcode);

				try {
					dbConnection.connection.prepareStatement("insert into people values (" + strData+");").execute();
				} catch (SQLException e) {
					// TODO: handle exception
					System.out.println("DBLoadData: Error to load Children!");
				}
			}
			if(pro instanceof YoungChild) {
				age = ((YoungChild) pro).getAge();
				strData = String.format("'%s'" +","+"'%s'"+","+"'%s'"+","+ "'%s'"+","+"'%d'"+ ","+"'%s'", strName,strImage,strStatus, strGender, age,strPostcode);
				
				try {
					dbConnection.connection.prepareStatement("insert into people values (" + strData+");").execute();
				} catch (SQLException e) {
					// TODO: handle exception
					System.out.println("DBLoadData: Error to load young child!");
				}
				
			}
		}
	}
	
	public void loadData() {
		try {
			rs = dbConnection.connection.prepareStatement("select * from people;").executeQuery();
			while(rs.next()) {
				System.out.println("\n");
				System.out.print(rs.getString(1) + "\t");
				System.out.print(rs.getString(2) + "\t");
				System.out.print(rs.getString(3) + "\t");
				System.out.print(rs.getString(4) + "\t");
				System.out.print(rs.getInt(5) + "\t");
				System.out.print(rs.getString(6) + "\t");
			}
			
			dbConnection.connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void main(String args[]) {
		DBLoadData dbload = new DBLoadData();
		dbload.loadData();
	}
}





