package View;

import java.awt.Color;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.List;
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
		loadDataModel(mainMenu.menu.getProfileList());
		this.add(btCancel);
		this.add(spProfileList);
	}
	
	
	
	public Object[] loadColumnName() {
		return new Object[] {"Name", "Image", "Status", "Gender", "Age", "Postcode"};
	}
	public Object[][] loadRowData(List<Profile> profileList){
		Object[][] obj = new Object[profileList.size()][6];
		int i = 0;
		for(Profile pro: profileList) {
			
			obj[i][0] = pro.getName();
			obj[i][1] = pro.getImage();
			obj[i][2] = pro.getStatus();
			obj[i][3] = pro.getGender();
			obj[i][5] = pro.getPostcode();
			if(pro instanceof Adult) {
				obj[i][4] = String.valueOf(((Adult)pro).getAge());
			}
			if(pro instanceof Children) {
				obj[i][4] = String.valueOf(((Children)pro).getAge());
			}
			if(pro instanceof YoungChild) {
				obj[i][4] = String.valueOf(((YoungChild)pro).getAge());
			}
			i++;
		}
		return obj;
	}
	public void loadDataModel(List<Profile> profileList) {
		dataModel = new DefaultTableModel(loadRowData(profileList), loadColumnName());
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
		PnDisplayPersonInfoGUI pndisplayPersonInro = new PnDisplayPersonInfoGUI(mainMenu, this);
		mainMenu.pnChanging.removeAll();
		String name = tbProfileList.getValueAt(tbProfileList.getSelectedRow(), 0).toString();
		String image = tbProfileList.getValueAt(tbProfileList.getSelectedRow(), 1).toString();
		String status = tbProfileList.getValueAt(tbProfileList.getSelectedRow(), 2).toString();
		String gender = tbProfileList.getValueAt(tbProfileList.getSelectedRow(), 3).toString();
		String age = tbProfileList.getValueAt(tbProfileList.getSelectedRow(), 4).toString();
		String postcode = tbProfileList.getValueAt(tbProfileList.getSelectedRow(), 5).toString();
		pndisplayPersonInro.lbName.setText(name);
		pndisplayPersonInro.lbImage.setText(image);
		pndisplayPersonInro.lbStatus.setText(status);
		pndisplayPersonInro.lbAge.setText(age);
		pndisplayPersonInro.lbGender.setText(gender);
		pndisplayPersonInro.lbPostcode.setText(postcode);
		mainMenu.pnChanging.add(pndisplayPersonInro);
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







