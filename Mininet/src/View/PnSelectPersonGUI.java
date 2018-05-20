package View;

import java.awt.Color;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Model.Adult;
import Model.Children;
import Model.Profile;
import Model.YoungChild;

public class PnSelectPersonGUI extends JPanel implements ActionListener, MouseListener{
	
	JScrollPane spProfileList = new JScrollPane();
	JTable tbProfileList = new JTable();
	TableModel dataModel = null;
	JButton btCancel = new JButton("Cancel");
	int _btWidth = 100;
	int _btHeight = 40;
	int _btX = 400;
	int _btY = 500;
	int _spWidth = 450;
	int _spHeight = 420;
	int _spX = 50;
	int _spY = 50;
	MainMenuGUI mainMenu;
	Map<String, Profile> hashMapProfile = new HashMap<String, Profile>();
	public PnSelectPersonGUI(MainMenuGUI mainMenu) {
		// TODO Auto-generated constructor stub
		this.mainMenu = mainMenu;
		setPanel();
		
	}
	public void setPanel() {
		
		this.setLayout(null);
		this.setBackground(Color.GREEN);
		btCancel.setBounds(_btX, _btY, _btWidth, _btHeight);
		spProfileList.setBounds(_spX, _spY, _spWidth, _spHeight);
		spProfileList.getViewport().add(tbProfileList);
		tbProfileList.addMouseListener(this);
		loadDataModel(mainMenu.dbLoadConnection.getProfilHashMap());
		this.add(btCancel);
		this.add(spProfileList);
	}
	public Object[] loadColumnName() {
		return new Object[] {"Name", "Image", "Status", "Gender", "Age", "Postcode"};
	}
	public Object[][] loadRowData(Map<String, Profile> profileHashMap){
		Object[][] obj = new Object[profileHashMap.size()][6];
		int i = 0;
		for(String name: profileHashMap.keySet()) {
			
			obj[i][0] = profileHashMap.get(name).getName();
			obj[i][1] = profileHashMap.get(name).getImage();
			obj[i][2] = profileHashMap.get(name).getStatus();
			obj[i][3] = profileHashMap.get(name).getGender();
			obj[i][5] = profileHashMap.get(name).getName();
			if(profileHashMap.get(name) instanceof Adult) {
				obj[i][4] = String.valueOf(((Adult)profileHashMap.get(name)).getAge());
			}
			if(profileHashMap.get(name) instanceof Children) {
				obj[i][4] = String.valueOf(((Children)profileHashMap.get(name)).getAge());
			}
			if(profileHashMap.get(name) instanceof YoungChild) {
				obj[i][4] = String.valueOf(((YoungChild)profileHashMap.get(name)).getAge());
			}
			i++;
		}
		return obj;
	}
	public void loadDataModel(Map<String, Profile> profileHashMap) {
		dataModel = new DefaultTableModel(loadRowData(profileHashMap), loadColumnName());
		tbProfileList.setModel(dataModel);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btCancel) {
			
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		mainMenu.pnChanging.removeAll();
		mainMenu.pnChanging.add(new PnDisplayPersonInfoGUI(mainMenu, this));
		mainMenu.pnChanging.repaint();
		mainMenu.pnChanging.revalidate();
//		System.out.println("Ahihihih");
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}







