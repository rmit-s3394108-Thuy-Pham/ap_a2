package View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Model.Profile;

public class PnRelationshipGUI extends JPanel implements ActionListener {
	
	JLabel lbPerson1 = new JLabel("Person1:");
	JLabel lbPerson2 = new JLabel("Person2:");
//	JComboBox<String> cbPersonList1 = new JComboBox<String>();
//	JComboBox<String> cbPersonList2 = new JComboBox<String>();
	JTextField tfPerson1 = new JTextField();
	JTextField tfPerson2 = new JTextField();
	JButton btCheckRelation = new JButton("Check Relation");
	JButton btDefineRelation = new JButton("Define Relation");
	int _btWidth = 120;
	int _btHeight = 35;
	int _lbWidth = 70;
	int _lbHeight = 35;
	int _cbWidth = 150;
	int _cbHeight = 40;
	int _lbY = 100;
	int _lbPerson1X = 50;
	int _gapX = 30;
	MainMenuGUI mainMenu;
	public PnRelationshipGUI(MainMenuGUI mainMenu) {
		this.mainMenu = mainMenu;
		this.setBackground(Color.pink);
		this.setLayout(null);
		setPanel();
	}
	public void setPanel() {
		lbPerson1.setBounds(_lbPerson1X, _lbY, _lbWidth, _lbHeight);
		tfPerson1.setBounds(_lbPerson1X + _lbWidth, _lbY, _cbWidth, _cbHeight);
		lbPerson2.setBounds(tfPerson1.getX() + tfPerson1.getWidth() + _gapX, _lbY, _lbWidth, _lbHeight);
		tfPerson2.setBounds(lbPerson2.getX() + lbPerson2.getWidth() , _lbY, _cbWidth, _cbHeight);
		btCheckRelation.setBounds(100, 200, _btWidth, _btHeight);
		btDefineRelation.setBounds(250, 200, _btWidth, _btHeight);
		btCheckRelation.addActionListener(this);
		btDefineRelation.addActionListener(this);
		this.add(lbPerson1);
		this.add(lbPerson2);
		this.add(tfPerson1);
		this.add(tfPerson2);
		this.add(btCheckRelation);
		this.add(btDefineRelation);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == btCheckRelation) {
			String strMessage = "";
			Profile pro1 = mainMenu.menu.objProReturn(tfPerson1.getText());
			Profile pro2 = mainMenu.menu.objProReturn(tfPerson2.getText());
			if(pro1 == null || pro2 == null) {
				JOptionPane.showMessageDialog(null, "Profile name does not exist!! please typing again!! ");
			}
			else {
				strMessage = mainMenu.menu.checkRelationshipT(pro1, pro2);
				JOptionPane.showMessageDialog(null, "Relation: " + strMessage);
			}
			tfPerson1.setText("");
			tfPerson2.setText("");
		}
		if(e.getSource() == btDefineRelation) {
			String[] options = {"Friend", "Colleaguel", "Classmate"};
		     int x = JOptionPane.showOptionDialog(null, "What relation do you want to define??? ",
		                "Click a button",
		                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		     if(x == 0) {
		    	 	System.out.println(x);
		    	 	if(mainMenu.menu.defineFriendRelations(mainMenu.menu.objProReturn(tfPerson1.getText()), mainMenu.menu.objProReturn(tfPerson2.getText())) == true) {
		    	 		JOptionPane.showMessageDialog(null, "Success to make friend!!!");
		    	 	}
		    	 	else {
		    	 		JOptionPane.showMessageDialog(null, "Failse to make friend!!!");
		    	 	}
		     }
		     if(x == 1) {
		    	 if(mainMenu.menu.defineColleaguelRelations(mainMenu.menu.objProReturn(tfPerson1.getText()), mainMenu.menu.objProReturn(tfPerson2.getText())) == true) {
		    	 		JOptionPane.showMessageDialog(null, "Success to make Colleaguel!!!");
		    	 	}
		    	 	else {
		    	 		JOptionPane.showMessageDialog(null, "Failse to make Colleaguel!!!");
		    	 	}
		     }
		     if(x == 2) {
		    	 if(mainMenu.menu.defineClassmateRelations(mainMenu.menu.objProReturn(tfPerson1.getText()), mainMenu.menu.objProReturn(tfPerson2.getText())) == true) {
		    	 		JOptionPane.showMessageDialog(null, "Success to make Classmate!!!");
		    	 	}
		    	 	else {
		    	 		JOptionPane.showMessageDialog(null, "Failse to make Classmate!!!");
		    	 	}
		     }
		}
	}
}








