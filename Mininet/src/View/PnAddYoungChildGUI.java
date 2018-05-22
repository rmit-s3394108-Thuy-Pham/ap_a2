package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Model.Adult;
import Model.Children;
import Model.Profile;

public class PnAddYoungChildGUI extends JPanel implements ActionListener {
	JLabel lbName = new JLabel("Name");
	JLabel lbImage = new JLabel("Image");
	JLabel lbAge = new JLabel("Age");
	JLabel lbStatus = new JLabel("Status");
	JLabel lbGender = new JLabel("Gender");
	JLabel lbPostcode = new JLabel("Postcode");
	JLabel lbDad = new JLabel("Dad");
	JLabel lbMom = new JLabel("Mom");
	JTextField tfName = new JTextField();
	JTextField tfImage = new JTextField();
	JTextField tfAge = new JTextField();
	JTextField tfStatus = new JTextField();
	JTextField tfPostccode = new JTextField();
	JTextField tfDad = new JTextField();
	JTextField tfMom = new JTextField();
	JRadioButton rbGenderM = new JRadioButton("Male");
	JRadioButton rbGenderF = new JRadioButton("Female");
	ButtonGroup bgGender = new ButtonGroup();
	JButton btAdd = new JButton("Add");
	int _lbX = 50;
	int _lbWidth = 70;
	int _lbHeight = 35;
	int _tfX = 150;
	int _tfWidth = 150;
	int _tfHeight = 35;
	int _rbWidth = 80;	
	int _rbHeight = 35;
	int _btWidth = 100;	
	int _btHeight = 40;
	int _gapY = 10;
	MainMenuGUI mainMenu;
	public PnAddYoungChildGUI(MainMenuGUI mainMenu) {
		this.mainMenu = mainMenu;
		this.setLayout(null);
		lbName.setBounds(_lbX, 50, _lbWidth, _lbHeight);
		tfName.setBounds(_tfX, lbName.getY(), _tfWidth, _tfHeight);
		lbImage.setBounds(_lbX, lbName.getY() + lbName.getHeight() + _gapY, _lbWidth, _lbHeight);
		tfImage.setBounds(_tfX, lbImage.getY() , _tfWidth, _tfHeight);
		lbAge.setBounds(_lbX, lbImage.getY() + lbImage.getHeight() + _gapY, _lbWidth, _lbHeight);
		tfAge.setBounds(_tfX, lbAge.getY(), _tfWidth, _tfHeight);
		lbStatus.setBounds(_lbX, lbAge.getY() + lbAge.getHeight() + _gapY, _lbWidth, _lbHeight);
		tfStatus.setBounds(_tfX, lbStatus.getY(), _tfWidth, _tfHeight);
		lbGender.setBounds(_lbX, lbStatus.getY() + lbStatus.getHeight() + _gapY, _lbWidth, _lbHeight);
		rbGenderM.setBounds(_tfX, lbGender.getY(), _rbWidth, _rbHeight);
		rbGenderF.setBounds(_tfX + rbGenderM.getWidth() + 20, lbGender.getY(), _rbWidth, _rbHeight);
		lbPostcode.setBounds(_lbX, lbGender.getY() + lbGender.getHeight() + _gapY, _lbWidth, _lbHeight);
		tfPostccode.setBounds(_tfX, lbPostcode.getY(), _tfWidth, _tfHeight);
		lbDad.setBounds(_lbX, lbPostcode.getY() + lbPostcode.getHeight() + _gapY, _lbWidth, _lbHeight);
		tfDad.setBounds(_tfX, lbDad.getY(), _tfWidth, _tfHeight);
		lbMom.setBounds(_lbX, lbDad.getY() + lbDad.getHeight() + _gapY, _lbWidth, _lbHeight);
		tfMom.setBounds(_tfX, lbMom.getY(), _tfWidth, _tfHeight);
		btAdd.setBounds(_tfX, lbMom.getY() + lbMom.getHeight() + 3*_gapY, _btWidth, _btHeight);
		bgGender.add(rbGenderF);
		bgGender.add(rbGenderM);
		btAdd.addActionListener(this);
		this.add(lbName);
		this.add(lbImage);
		this.add(lbAge);
		this.add(lbStatus);
		this.add(lbPostcode);
		this.add(lbGender);
		this.add(lbDad);
		this.add(lbMom);
		this.add(tfName);
		this.add(tfImage);
		this.add(tfAge);
		this.add(tfStatus);
		this.add(tfPostccode);
		this.add(tfDad);
		this.add(tfMom);
		this.add(rbGenderF);
		this.add(rbGenderM);
		this.add(btAdd);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btAdd) {
			boolean check = true;
			int i = 0;
			String strName = tfName.getText();
			String strImage = tfImage.getText();
			int age = 0;
			try {
				age = Integer.parseInt(tfAge.getText());
			}catch(NumberFormatException e1) {
				check = false;
//				i++;
			}
			if(age > 3) {
				check = false;
//				i++;
			}
			String strStatus = tfStatus.getText();
			String strGender = ""; 
			String strPostcode = tfPostccode.getText();
			String strDad = tfDad.getText();
			String strMom = tfMom.getText();
			if(rbGenderM.isSelected()) {
				strGender = rbGenderM.getText();
			}
			if(rbGenderF.isSelected()) {
				strGender = rbGenderF.getText();
			}
			if(tfName.getText().equals("")) {
				check = false;
//				JOptionPane.showMessageDialog(null, "Failse!!!   Name can not be emptied!!");
			}
			for(Profile pro: this.mainMenu.menu.getProfileList()) {
				if(tfName.getText().equals(pro.getName())) {
					check = false;
					break;
				}
			}
			if(tfDad.getText().equals(tfMom.getText())) {
				check = false;
			}
			else {
				for(Profile pro: this.mainMenu.menu.getProfileList()) {
//					check = false;
					if(tfDad.getText().equals(pro.getName())) {
						i++;
					}
					if(tfMom.getText().equals(pro.getName())) {
						i++;
					}
				}
				if(i != 2) {
					check = false;
				}
			}
			if(check == true) {
				this.mainMenu.menu.getProfileList().add(new Children(strName, strImage, strStatus, strGender, strPostcode, age, (Adult)mainMenu.menu.objProReturn(strDad), (Adult)mainMenu.menu.objProReturn(strMom)));
				tfName.setText("");
				tfImage.setText("");
				tfAge.setText("");
				tfStatus.setText("");
				rbGenderM.setSelected(true);
				tfPostccode.setText("");
				tfDad.setText("");
				tfMom.setText("");
				JOptionPane.showMessageDialog(null, "Success!! A new Children is created!");
			}
			else {
				JOptionPane.showMessageDialog(null, "Failse!! New Children is not created!!!!");
			}
		}
	}
}
