package View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PnRelationshipGUI extends JPanel implements ActionListener {
	
	JLabel lbPerson1 = new JLabel("Person:");
	JLabel lbPerson2 = new JLabel("Person:");
	JComboBox<String> cbPersonList1 = new JComboBox<String>();
	JComboBox<String> cbPersonList2 = new JComboBox<String>();
	JButton btCheckRelation = new JButton("Check Relation");
	JButton btDefineRelation = new JButton("Define Relation");
	int _btWidth = 120;
	int _btHeight = 35;
	int _lbWidth = 50;
	int _lbHeight = 35;
	int _cbWidth = 150;
	int _cbHeight = 40;
	int _lbY = 100;
	int _lbPerson1X = 50;
	int _gapX = 70;
	public PnRelationshipGUI() {
		this.setBackground(Color.pink);
		this.setLayout(null);
		setPanel();
	}
	public void setPanel() {
		lbPerson1.setBounds(_lbPerson1X, _lbY, _lbWidth, _lbHeight);
		cbPersonList1.setBounds(_lbPerson1X + _lbWidth, _lbY, _cbWidth, _cbHeight);
		lbPerson2.setBounds(cbPersonList1.getX() + cbPersonList1.getWidth() + _gapX, _lbY, _lbWidth, _lbHeight);
		cbPersonList2.setBounds(lbPerson2.getX() + lbPerson2.getWidth() , _lbY, _cbWidth, _cbHeight);
		btCheckRelation.setBounds(100, 200, _btWidth, _btHeight);
		btDefineRelation.setBounds(250, 200, _btWidth, _btHeight);
		this.add(lbPerson1);
		this.add(lbPerson2);
		this.add(cbPersonList1);
		this.add(cbPersonList2);
		this.add(btCheckRelation);
		this.add(btDefineRelation);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
