package DBConnection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.Adult;
import Model.Children;
import Model.Profile;
import Model.ReadFile;
import Model.YoungChild;

public class DBLoadData {
	private List<Profile> profileList = new ArrayList<Profile>();
	private Map<String, Profile> profileHashMap = new HashMap<String, Profile>();
	DBConnection dbConnection = new DBConnection();
	ReadFile readFile; 
	ResultSet rs = null;
	BufferedReader input = null;
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
//		loadData();
//		setRelations();
	}
	
	public void loadData() {
		String strName;
		String strImage;
		String strStatus;
		String strGender;
		int age;
		String strPostcode;
		try {
			rs = dbConnection.connection.prepareStatement("select * from people;").executeQuery();
			while(rs.next()) {
				strName = rs.getString(1);
				strImage = rs.getString(2);
				strStatus = rs.getString(3);
				strGender = rs.getString(4);
				age = rs.getInt(5);
				strPostcode = rs.getString(6);
				if(age > 16) {
//					profileList.add(new Adult(strName, strImage, strStatus, strGender, strPostcode, age));
					profileHashMap.put(strName, new Adult(strName, strImage, strStatus, strGender, strPostcode, age));
				}
				if(age <= 16 && age > 2) {
//					profileList.add(new Children(strName, strImage, strStatus, strGender, strPostcode, age));
					profileHashMap.put(strName, new Children(strName, strImage, strStatus, strGender, strPostcode, age));
				}
				if(age <= 2) {
//					profileList.add(new YoungChild(strName, strImage, strStatus, strGender, strPostcode, age));
					profileHashMap.put(strName, new YoungChild(strName, strImage, strStatus, strGender, strPostcode, age));
				}
//				System.out.println("Name: " + profileHashMap.get(strName).getName() + "\t");
//				System.out.println("Image: " + profileHashMap.get(strName).getImage() + "\t");
//				System.out.println("Status: " + profileHashMap.get(strName).getStatus() + "\t");
//				System.out.println("Gender: " + profileHashMap.get(strName).getGender() + "\t");
//				System.out.println("Postcode: " + profileHashMap.get(strName).getPostcode() + "\t");
			}
			
			dbConnection.connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void main(String args[]) {
		DBLoadData dbload = new DBLoadData();
//		dbload.loadData();
		for(String name: dbload.profileHashMap.keySet()) {
			System.out.println("Name: " + dbload.profileHashMap.get(name).getName() + "\t");
			System.out.println("Image: " + dbload.profileHashMap.get(name).getImage() + "\t");
			System.out.println("Status: " + dbload.profileHashMap.get(name).getStatus() + "\t");
			System.out.println("Gender: " + dbload.profileHashMap.get(name).getGender() + "\t");
			System.out.println("Postcode: " + dbload.profileHashMap.get(name).getPostcode() + "\t");
			if(dbload.profileHashMap.get(name) instanceof Adult) {
				System.out.println("Age: " + ((Adult)dbload.profileHashMap.get(name)).getAge());
				System.out.println("---------THis is an adult!----------");
			}
			if(dbload.profileHashMap.get(name) instanceof Children) {
				System.out.println("Age: " + ((Children)dbload.profileHashMap.get(name)).getAge());
				System.out.println("---------THis is an CHildren!--------");
			}
			if(dbload.profileHashMap.get(name) instanceof YoungChild) {
				System.out.println("Age: " + ((YoungChild)dbload.profileHashMap.get(name)).getAge());
				System.out.println("---------THis is an YoungChild!-------");
			}
			System.out.println(dbload.profileHashMap.size());
		}
	}
	public void setRelations() {
		String line;
		String str[];
//		}
		try {
			input = new BufferedReader(new FileReader("document/relations.txt"));
			line = input.readLine();
			while(line != null) {
				str = line.split(",");
				if(str[2].equals("friends")) {
					profileHashMap.get(str[0]).addFriend(profileHashMap.get(str[1]));
					profileHashMap.get(str[1]).addFriend(profileHashMap.get(str[0]));
				}
				if(str[2].equals("couple")) {
					
				}
				if(str[2].equals("parent")) {
					if(profileHashMap.get(str[0]) instanceof Adult) {
//						((Adult)profileHashMap.get(str[0])).getChildrenList().add(profileHashMap.get(str[1]));
//						if(((Children)profileHashMap.get(str[1])).getParent1() == null) {
//							((Children)profileHashMap.get(str[1])).setParent1(((Adult)profileHashMap.get(str[0])));
//						}
//						else {
//							((Children)profileHashMap.get(str[1])).setParent2(((Adult)profileHashMap.get(str[0])));
//						}
					}
					if(profileHashMap.get(str[1]) instanceof Adult) {
//						((Adult)profileHashMap.get(str[1])).getChildrenList().add(profileHashMap.get(str[0]));
//						if(((Children)profileHashMap.get(str[0])).getParent1() == null) {
//							((Children)profileHashMap.get(str[0])).setParent1(((Adult)profileHashMap.get(str[1])));
//						}
//						else {
//							((Children)profileHashMap.get(str[0])).setParent2(((Adult)profileHashMap.get(str[1])));
//						}
					}
				}
				if(str[2].equals("colleagues")) {
					((Adult)profileHashMap.get(str[0])).addColleague(((Adult)profileHashMap.get(str[1])));
					((Adult)profileHashMap.get(str[1])).addColleague(((Adult)profileHashMap.get(str[2])));
				}
				if(str[2].equals("classmates")) {
					profileHashMap.get(str[0]).addClassmate(profileHashMap.get(str[1]));
					profileHashMap.get(str[1]).addClassmate(profileHashMap.get(str[0]));
				}
				
				line = input.readLine();
			}
			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<Profile> getProfileList() {
		return profileList;
	}

	public void setProfileList(List<Profile> profileList) {
		this.profileList = profileList;
	}
	public Map<String, Profile> getProfilHashMap() {
		return profileHashMap;
	}

	public void setProfileHashMap(Map<String, Profile> profileHashMap) {
		this.profileHashMap = profileHashMap;
	}
}





