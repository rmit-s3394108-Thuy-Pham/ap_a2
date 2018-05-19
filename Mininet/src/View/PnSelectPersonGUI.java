package View;

import java.awt.Color;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
		setPanel();
		this.mainMenu = mainMenu;
	}
	public void setPanel() {
		this.setLayout(null);
		this.setBackground(Color.GREEN);
		btCancel.setBounds(_btX, _btY, _btWidth, _btHeight);
		spProfileList.setBounds(_spX, _spY, _spWidth, _spHeight);
		spProfileList.getViewport().add(tbProfileList);
		tbProfileList.addMouseListener(this);
		loadDataModel();
		this.add(btCancel);
		this.add(spProfileList);
	}
	public Object[] loadColumnName() {
		return new Object[] {"Name", "Age"};
	}
	public Object[][] loadRowData(){
		return new Object[][] {{"trung", "12"}, {"Kevin", "14"}};
	}
	public void loadDataModel() {
		dataModel = new DefaultTableModel(loadRowData(), loadColumnName());
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
		mainMenu.pnChanging.removeAll();
		mainMenu.pnChanging.add(new PnDisplayPersonInfoGUI(mainMenu, this));
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







