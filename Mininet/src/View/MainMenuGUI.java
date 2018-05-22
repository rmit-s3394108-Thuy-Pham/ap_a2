package View;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import DBConnection.DBLoadData;
import Model.CustomFileNotFoundException;
import Model.Menu;
import imageProcess.ImagePro;

public class MainMenuGUI extends JFrame implements ActionListener {
	
	JPanel pnMenu = new JPanel();
	JPanel pnChanging = new JPanel();
	JButton btAddPerson = new JButton("Add Person");
	JButton btSelectPerson = new JButton("Select Person");
	JButton btRelationship = new JButton("RelationShip");
	JButton btFindoutRelations = new JButton("Find out");
	JButton btExit = new JButton("Exit");
	JLabel lbBackground = new JLabel("");
	ImagePro imagePro = new ImagePro();
	int _btWidth = 120;
	int _btHeight = 35;
	int _btX = 10;
	int _btAddPersonY = 50;
	int _gap = 30;
	int _frameWidth = 700;
	int _frameHeight = 600;
	int _frameX = 400;
	int _frameY = 150;
	int _pnMenuWidth = 150;
	int _pnChangingWidth = 550;
	
	Menu menu; ;
	public MainMenuGUI() throws CustomFileNotFoundException {
		menu = new Menu();
		setFrame();
	}
	public void setFrame() {
//		JOptionPane.dialog
		this.setLayout(null);
		pnMenu.setLayout(null);
		pnChanging.setLayout(new CardLayout());
		btAddPerson.setBounds(_btX, _btAddPersonY, _btWidth, _btHeight);
		btSelectPerson.setBounds(_btX, _btAddPersonY + _btHeight + _gap, _btWidth, _btHeight);
		btRelationship.setBounds(_btX, btSelectPerson.getY() + _btHeight + _gap, _btWidth, _btHeight);
		btFindoutRelations.setBounds(_btX, btRelationship.getY() + _btHeight + _gap, _btWidth, _btHeight);
		btExit.setBounds(_btX, btFindoutRelations.getY() + _btHeight + _gap, _btWidth, _btHeight);
		pnMenu.setSize(_pnMenuWidth, _frameHeight);
		pnChanging.setBounds(_pnMenuWidth, 0, _pnChangingWidth, _frameHeight);
		lbBackground.setSize(_btWidth, _btHeight);
		lbBackground.setIcon(imagePro.iMovieContent);
		pnMenu.setBackground(Color.BLACK);
		pnChanging.setBackground(Color.BLUE);
		btExit.addActionListener(this);
		btSelectPerson.addActionListener(this);
		btRelationship.addActionListener(this);
		btFindoutRelations.addActionListener(this);
		btAddPerson.addActionListener(this);
		pnMenu.add(btAddPerson);
		pnMenu.add(btSelectPerson);
		pnMenu.add(btRelationship);
		pnMenu.add(btFindoutRelations);
		pnMenu.add(btExit);
		pnChanging.add(lbBackground);
		this.setBounds(_frameX, _frameY,_frameWidth, _frameHeight);
		this.add(pnMenu);
		this.add(pnChanging);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
//	public static void main(String[] args) throws CustomFileNotFoundException {
//		// TODO Auto-generated method stub
//		new MainMenuGUI();
//		
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btAddPerson) {
			 String[] options = {"Adult", "Children", "Youngchild"};
		     int x = JOptionPane.showOptionDialog(null, "Choose one option do you want to create!",
		                "Click a button",
		                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		     
		     if(x == 0) {
		    	 	pnChanging.removeAll();
				pnChanging.add(new PnAddAdultGUI(this));
				pnChanging.repaint();
				pnChanging.revalidate();
		     }
		     if(x == 1) {
		    	 	pnChanging.removeAll();
				pnChanging.add(new PnAddChildrenGUI(this));
				pnChanging.repaint();
				pnChanging.revalidate();
		     }
		     if(x == 2) {
		    	 	pnChanging.removeAll();
				pnChanging.add(new PnAddYoungChildGUI(this));
				pnChanging.repaint();
				pnChanging.revalidate();
		     }
		}
		if(e.getSource() == btSelectPerson) {
			pnChanging.removeAll();
			pnChanging.add(new PnSelectPersonGUI(this));
			pnChanging.repaint();
			pnChanging.revalidate();
		}
		if(e.getSource() == btRelationship) {
			pnChanging.removeAll();
			pnChanging.add(new PnRelationshipGUI(this));
			pnChanging.repaint();
			pnChanging.revalidate();
		}
		if(e.getSource() == btFindoutRelations) {
			pnChanging.removeAll();
			pnChanging.add(new PnFindoutRelations(this));
			pnChanging.repaint();
			pnChanging.revalidate();
			
		}
		if(e.getSource() == btExit) {
			System.exit(0);
		}
	}
}




