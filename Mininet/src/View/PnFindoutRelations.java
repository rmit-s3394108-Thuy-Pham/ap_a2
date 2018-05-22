package View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Model.Adult;
import Model.Children;
import Model.Profile;
import Model.YoungChild;
import javafx.scene.control.Alert;

public class PnFindoutRelations extends JPanel implements ActionListener, MouseListener {
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
	public PnFindoutRelations(MainMenuGUI mainMenu) {
		// TODO Auto-generated constructor stub
		this.mainMenu = mainMenu;
		setPanel();
	}
	public void setPanel() {
		this.setLayout(null);
		this.setBackground(Color.GRAY);
		spProfileList.setBounds(_spX, _spY, _spWidth, _spHeight);
		spProfileList.getViewport().add(tbProfileList);
		tbProfileList.addMouseListener(this);
		loadDataModel(mainMenu.menu.getProfileList());
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
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		String strMember = "";
		String name = tbProfileList.getValueAt(tbProfileList.getSelectedRow(), 0).toString();
		String image = tbProfileList.getValueAt(tbProfileList.getSelectedRow(), 1).toString();
		String status = tbProfileList.getValueAt(tbProfileList.getSelectedRow(), 2).toString();
		String gender = tbProfileList.getValueAt(tbProfileList.getSelectedRow(), 3).toString();
		String age = tbProfileList.getValueAt(tbProfileList.getSelectedRow(), 4).toString();
		String postcode = tbProfileList.getValueAt(tbProfileList.getSelectedRow(), 5).toString();
		Profile pro = mainMenu.menu.objProReturn(name);
		if(pro instanceof Adult) {
			for(Profile familyMember: mainMenu.menu.findOutFamilyRelationT(pro)) {
				if(familyMember instanceof Children) {
					strMember += ("Child: " + familyMember.getName() + "\n");
				}
				if(familyMember instanceof Adult) {
					strMember += ("Couple: " + familyMember.getName() + "\n");
				}
			}
		}
		if(pro instanceof Children) {
			for(Profile familyMember: mainMenu.menu.findOutFamilyRelationT(pro)) {
				if(familyMember instanceof Children) {
					strMember += ("sibling: " + familyMember.getName() + "\n");
				}
				if(familyMember instanceof Adult) {
					strMember += ("Parent: " + familyMember.getName() + "\n");
				}
			}
		}
		if(strMember.equals("")) {
			strMember = "There is no family member";
		}
		JOptionPane.showMessageDialog(null, strMember);
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
