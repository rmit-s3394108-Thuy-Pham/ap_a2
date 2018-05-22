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
	public void setFriendList(Profile pro) {
		for(Profile pro1: pro.getFriendList()) {
			
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		PnDisplayAdultInfoGUI pndisplayAdultInro = new PnDisplayAdultInfoGUI(mainMenu, this);
		PnDisplayChildrenInfoGUI pndisplayChildrenInro = new PnDisplayChildrenInfoGUI(mainMenu, this);
		PnDisplayYoungChildInfoGUI pndisplayYoungChildInro = new PnDisplayYoungChildInfoGUI(mainMenu, this);
		mainMenu.pnChanging.removeAll();
		String name = tbProfileList.getValueAt(tbProfileList.getSelectedRow(), 0).toString();
		String image = tbProfileList.getValueAt(tbProfileList.getSelectedRow(), 1).toString();
		String status = tbProfileList.getValueAt(tbProfileList.getSelectedRow(), 2).toString();
		String gender = tbProfileList.getValueAt(tbProfileList.getSelectedRow(), 3).toString();
		String age = tbProfileList.getValueAt(tbProfileList.getSelectedRow(), 4).toString();
		String postcode = tbProfileList.getValueAt(tbProfileList.getSelectedRow(), 5).toString();
		
		if(mainMenu.menu.objProReturn(name) instanceof Adult) {
			pndisplayAdultInro.lbName.setText(name);
			pndisplayAdultInro.lbImage.setText(image);
			pndisplayAdultInro.lbStatus.setText(status);
			pndisplayAdultInro.lbAge.setText(age);
			pndisplayAdultInro.lbGender.setText(gender);
			pndisplayAdultInro.lbPostcode.setText(postcode);
			for(Profile pro1: mainMenu.menu.objProReturn(name).getFriendList()) {
				pndisplayAdultInro.modelFriend.addElement(pro1.getName());
				
			}
			for(Profile pro1: ((Adult)mainMenu.menu.objProReturn(name)).getColleaguelist()) {
				pndisplayAdultInro.modelColleaguel.addElement(pro1.getName());
				
			}
			for(Profile pro1: ((Adult)mainMenu.menu.objProReturn(name)).getClassmatesList()) {
				pndisplayAdultInro.modelClassmate.addElement(pro1.getName());
				
			}
			for(Profile pro1: ((Adult)mainMenu.menu.objProReturn(name)).getChildrenList()) {
				pndisplayAdultInro.modelChild.addElement(pro1.getName());
			}
			for(Profile pro1: ((Adult)mainMenu.menu.objProReturn(name)).getYoungChildList()) {
				pndisplayAdultInro.modelChild.addElement(pro1.getName());
			}
			
			pndisplayAdultInro.cbFriendList.setModel(pndisplayAdultInro.modelFriend);
			pndisplayAdultInro.cbColleaguelList.setModel(pndisplayAdultInro.modelColleaguel);
			pndisplayAdultInro.cbClassmateList.setModel(pndisplayAdultInro.modelClassmate);
			pndisplayAdultInro.cbChildList.setModel(pndisplayAdultInro.modelChild);
			mainMenu.pnChanging.add(pndisplayAdultInro);
			mainMenu.pnChanging.repaint();
			mainMenu.pnChanging.revalidate();
		}
		if(mainMenu.menu.objProReturn(name) instanceof Children) {
			pndisplayChildrenInro.lbName.setText(name);
			pndisplayChildrenInro.lbImage.setText(image);
			pndisplayChildrenInro.lbStatus.setText(status);
			pndisplayChildrenInro.lbAge.setText(age);
			pndisplayChildrenInro.lbGender.setText(gender);
			pndisplayChildrenInro.lbPostcode.setText(postcode);
			
			for(Profile pro1: mainMenu.menu.objProReturn(name).getFriendList()) {
				pndisplayChildrenInro.modelFriend.addElement(pro1.getName());
				pndisplayChildrenInro.cbFriendList.setModel(pndisplayChildrenInro.modelFriend);
			}
			
			for(Profile pro1: ((Children)mainMenu.menu.objProReturn(name)).getClassmatesList()) {
				pndisplayChildrenInro.modelClassmate.addElement(pro1.getName());
				pndisplayChildrenInro.cbClassmateList.setModel(pndisplayChildrenInro.modelClassmate);
			}
			pndisplayChildrenInro.lbvlParent1.setText(((Children)mainMenu.menu.objProReturn(name)).getParent1().getName());
			pndisplayChildrenInro.lbvlParent2.setText(((Children)mainMenu.menu.objProReturn(name)).getParent2().getName());
			
			
			mainMenu.pnChanging.add(pndisplayChildrenInro);
			mainMenu.pnChanging.repaint();
			mainMenu.pnChanging.revalidate();
		}
		if(mainMenu.menu.objProReturn(name) instanceof YoungChild) {
			pndisplayYoungChildInro.lbName.setText(name);
			pndisplayYoungChildInro.lbImage.setText(image);
			pndisplayYoungChildInro.lbStatus.setText(status);
			pndisplayYoungChildInro.lbAge.setText(age);
			pndisplayYoungChildInro.lbGender.setText(gender);
			pndisplayYoungChildInro.lbPostcode.setText(postcode);
			
			pndisplayYoungChildInro.lbvlParent1.setText(((YoungChild)mainMenu.menu.objProReturn(name)).getParent1().getName());
			pndisplayYoungChildInro.lbvlParent2.setText(((YoungChild)mainMenu.menu.objProReturn(name)).getParent2().getName());
			
			
			mainMenu.pnChanging.add(pndisplayYoungChildInro);
			mainMenu.pnChanging.repaint();
			mainMenu.pnChanging.revalidate();
		}
		
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







