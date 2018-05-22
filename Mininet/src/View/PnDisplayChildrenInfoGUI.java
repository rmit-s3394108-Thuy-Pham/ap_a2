package View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Model.Menu;
import Model.Profile;
import imageProcess.ImagePro;

public class PnDisplayChildrenInfoGUI extends JPanel implements ActionListener{
	
	JButton btDelete = new JButton("Delete");
	JButton btCancel = new JButton("Cancel");
	JLabel lbNamelb = new JLabel("Name: ");
	JLabel lbName = new JLabel("Kevin");
	JLabel lbAgelb = new JLabel("Age: ");
	JLabel lbAge = new JLabel("20");
	JLabel lbStatuslb = new JLabel("Status:");
	JLabel lbStatus = new JLabel("I am a RMIT student");
	JLabel lbImage = new JLabel("non image");
	JLabel lbGenderlb = new JLabel("Gender: ");
	JLabel lbGender = new JLabel("M");
	JLabel lbPostcodelb = new JLabel("Postcode");
	JLabel lbPostcode = new JLabel("VIC");
	JLabel lbFriendList = new JLabel("Friend List");
	JComboBox<String> cbFriendList = new JComboBox<>();
	JLabel lbClassmateList = new JLabel("Classmate List");
	JComboBox<String> cbClassmateList = new JComboBox<>();
	JLabel lbParent1 = new JLabel("Dad");
	JLabel lbvlParent1 = new JLabel("Kevin");
	JLabel lbParent2 = new JLabel("Mom");
	JLabel lbvlParent2 = new JLabel("Thuy");
	DefaultComboBoxModel modelFriend = new DefaultComboBoxModel<>();
	DefaultComboBoxModel modelClassmate = new DefaultComboBoxModel<>();
	int _btWidth = 100;
	int _btHeight = 40;
	int _btDeleteX = 200;
	int _btDeleteY = 500;
	int _btCancelX = 400;
	int _btCancelY = 500;
	int _lblbWidth = 100;
	int _lblbHeight = 35;
	int _lbvlWidth = 150;
	int _lbvlHeight = 35;
	int _lblbX = 200;
	int _lbvlX = 300;
	int _cbWidth = 150;
	int _cbHeight = 35;
	int _gapX = 10;
	int _gapY = 10;
	int _lbNamelbY = 50;
	MainMenuGUI mainMenu;
	PnSelectPersonGUI pnSelect;
	public PnDisplayChildrenInfoGUI(MainMenuGUI mainMenu, PnSelectPersonGUI pnSelect) {
		// TODO Auto-generated constructor stub
		this.mainMenu = mainMenu;
		this.pnSelect = pnSelect;
		this.setBackground(Color.YELLOW);
		this.setLayout(null);
		setPanel();
	
		this.btCancel.addActionListener(this);
		this.btDelete.addActionListener(this);
		this.add(lbNamelb);
		this.add(lbName);
		this.add(lbAgelb);
		this.add(lbAge);
		this.add(lbStatuslb);
		this.add(lbStatus);
		this.add(lbImage);
		this.add(lbGenderlb);
		this.add(lbGender);
		this.add(lbPostcodelb);
		this.add(lbPostcode);
		this.add(lbFriendList);
		this.add(cbFriendList);
		this.add(lbClassmateList);
		this.add(cbClassmateList);
		this.add(lbParent1);
		this.add(lbvlParent1);
		this.add(lbParent2);
		this.add(lbvlParent2);
		this.add(btDelete);
		this.add(btCancel);
		
	}
	public void setPanel() {
		btDelete.setBounds(_btDeleteX, _btDeleteY, _btWidth, _btHeight);
		btCancel.setBounds(_btCancelX, _btCancelY, _btWidth, _btHeight);
		lbImage.setBounds(30, 50, 200, 200);
		lbNamelb.setBounds(_lblbX, _lbNamelbY, _lblbWidth, _lblbHeight);
		lbName.setBounds(_lbvlX, _lbNamelbY, _lbvlWidth, _lbvlHeight);
		lbAgelb.setBounds(_lblbX, _lbNamelbY + _lblbHeight + _gapY, _lblbWidth, _lblbHeight);
		lbAge.setBounds(_lbvlX, lbAgelb.getY(), _lbvlWidth, _lbvlHeight);
		lbStatuslb.setBounds(_lblbX, lbAgelb.getY() + _lblbHeight + _gapY, _lblbWidth, _lblbHeight);
		lbStatus.setBounds(_lbvlX, lbStatuslb.getY(), _lbvlWidth, _lbvlHeight);
		lbGenderlb.setBounds(_lblbX, lbStatuslb.getY() + _lblbHeight + _gapY, _lblbWidth, _lblbHeight);
		lbGender.setBounds(_lbvlX, lbStatuslb.getY() + _lblbHeight + _gapY, _lbvlWidth, _lbvlHeight);
		lbPostcodelb.setBounds(_lblbX, lbGenderlb.getY() + _lblbHeight + _gapY, _lblbWidth, _lblbHeight);
		lbPostcode.setBounds(_lbvlX, lbGenderlb.getY() + _lblbHeight + _gapY, _lblbWidth, _lblbHeight);
		lbFriendList.setBounds(_lblbX, lbPostcode.getY() + lbPostcode.getHeight() + _gapY, _lblbWidth, _lblbHeight);
		cbFriendList.setBounds(_lbvlX, lbFriendList.getY(), _cbWidth, _cbHeight);
		lbClassmateList.setBounds(_lblbX, lbFriendList.getY() + lbFriendList.getHeight() + _gapY, _lblbWidth, _lblbHeight);
		cbClassmateList.setBounds(_lbvlX, lbClassmateList.getY(), _cbWidth, _cbHeight);
		lbParent1.setBounds(_lblbX, lbClassmateList.getY() + lbClassmateList.getHeight() + _gapY, _lblbWidth, _lblbHeight);
		lbvlParent1.setBounds(_lbvlX, lbParent1.getY(), _cbWidth, _cbHeight);
		lbParent2.setBounds(_lblbX, lbParent1.getY() + lbParent1.getHeight() + _gapY, _lblbWidth, _lblbHeight);
		lbvlParent2.setBounds(_lbvlX, lbParent2.getY(), _cbWidth, _cbHeight);
		lbImage.setIcon(new ImagePro().iUsername);
	}
	public void setFriendList(Profile pro) {
//		if() {
//			
//		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btCancel) {
			this.mainMenu.pnChanging.removeAll();
			this.mainMenu.pnChanging.add(pnSelect);
			this.mainMenu.repaint();
			this.mainMenu.revalidate();
		}
		if(e.getSource() == btDelete) {
			int i = 0;
			for(Profile pro: mainMenu.menu.getProfileList()) {
				if(pro.getName().equals(lbName.getText())) {
					mainMenu.menu.deleteProfileInfo(pro);
					break;
				}
			}
			pnSelect.loadDataModel(mainMenu.menu.getProfileList());
			this.mainMenu.pnChanging.removeAll();
			this.mainMenu.pnChanging.add(pnSelect);
			this.mainMenu.repaint();
			this.mainMenu.revalidate();
//			this.mainMenu.pnChanging.removeAll();
//			this.mainMenu.pnChanging.add(pnSelect);
//			this.mainMenu.repaint();
//			this.mainMenu.revalidate();
//			mainMenu.menu.deleteProfileInfo(pro);
		}
	}

}






