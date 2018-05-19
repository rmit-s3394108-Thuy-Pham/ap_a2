package View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import imageProcess.ImagePro;

public class PnDisplayPersonInfoGUI extends JPanel implements ActionListener{
	
	JButton btDelete = new JButton("Delete");
	JButton btCancel = new JButton("Cancel");
	JLabel lbNamelb = new JLabel("Name: ");
	JLabel lbName = new JLabel("Kevin");
	JLabel lbAgelb = new JLabel("Age: ");
	JLabel lbAge = new JLabel("20");
	JLabel lbStatuslb = new JLabel("Status:");
	JLabel lbStatus = new JLabel("I am a RMIT student");
	JLabel lbImage = new JLabel("non image");
	int _btWidth = 100;
	int _btHeight = 40;
	int _btDeleteX = 200;
	int _btDeleteY = 500;
	int _btCancelX = 400;
	int _btCancelY = 500;
	int _lblbWidth = 100;
	int _lblbHeight = 40;
	int _lbvlWidth = 150;
	int _lbvlHeight = 40;
	int _lblbX = 200;
	int _lbvlX = 300;
	int _gapX = 10;
	int _gapY = 30;
	int _lbNamelbY = 50;
	MainMenuGUI mainMenu;
	PnSelectPersonGUI pnSelect;
	public PnDisplayPersonInfoGUI(MainMenuGUI mainMenu, PnSelectPersonGUI pnSelect) {
		// TODO Auto-generated constructor stub
		this.mainMenu = mainMenu;
		this.pnSelect = pnSelect;
		this.setBackground(Color.YELLOW);
		this.setLayout(null);
		setPanel();
		this.btCancel.addActionListener(this);
		this.add(lbNamelb);
		this.add(lbName);
		this.add(lbAgelb);
		this.add(lbAge);
		this.add(lbStatuslb);
		this.add(lbStatus);
		this.add(lbImage);
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
		lbImage.setIcon(new ImagePro().iUsername);
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
	}

}
