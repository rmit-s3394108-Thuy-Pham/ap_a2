package View;

import java.awt.CardLayout;
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
import Model.Profile;
import javafx.scene.control.Button;

public class PnAddAdultGUI extends JPanel implements ActionListener{
	JLabel lbTitle = new JLabel("Add Adult Panel");
	JLabel lbName = new JLabel("Name");
	JLabel lbImage = new JLabel("Image");
	JLabel lbAge = new JLabel("Age");
	JLabel lbStatus = new JLabel("Status");
	JLabel lbGender = new JLabel("Gender");
	JLabel lbPostcode = new JLabel("Postcode");
	JTextField tfName = new JTextField();
	JTextField tfImage = new JTextField();
	JTextField tfAge = new JTextField();
	JTextField tfStatus = new JTextField();
	JTextField tfPostccode = new JTextField();
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
	public PnAddAdultGUI(MainMenuGUI mainMenu) {
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
		btAdd.setBounds(_tfX, lbPostcode.getY() + lbPostcode.getHeight() + 3*_gapY, _btWidth, _btHeight);
		rbGenderM.setSelected(true);
		bgGender.add(rbGenderF);
		bgGender.add(rbGenderM);
		btAdd.addActionListener(this);
		this.add(lbName);
		this.add(lbImage);
		this.add(lbAge);
		this.add(lbStatus);
		this.add(lbPostcode);
		this.add(lbGender);
		this.add(tfName);
		this.add(tfImage);
		this.add(tfAge);
		this.add(tfStatus);
		this.add(tfPostccode);
		this.add(rbGenderF);
		this.add(rbGenderM);
		this.add(btAdd);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btAdd) {
			boolean check = true;
			String strName = tfName.getText();
			String strImage = tfImage.getText();
			int age = 0;
			try {
				age = Integer.parseInt(tfAge.getText());
			}catch(NumberFormatException e1) {
				check = false;
			}
			if(age <= 16 || age >= 150) {
				check = false;
			}
			String strStatus = tfStatus.getText();
			String strGender = ""; 
			String strPostcode = tfPostccode.getText();
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
			if(check == true) {
				this.mainMenu.menu.getProfileList().add(new Adult(strName, strImage, strStatus, strGender, strPostcode, age));
				tfName.setText("");
				tfImage.setText("");
				tfAge.setText("");
				tfStatus.setText("");
				rbGenderM.setSelected(true);
				tfPostccode.setText("");
				JOptionPane.showMessageDialog(null, "Success!! A new Adult is created!");
			}
			else {
				JOptionPane.showMessageDialog(null, "Failse!! New Adult is not created!!!!");
			}
		}
			
	}
	
	
}






