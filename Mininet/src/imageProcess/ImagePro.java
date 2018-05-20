package imageProcess;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImagePro {
	public ImageIcon iUsername;
	public ImageIcon iPassword;
	public ImageIcon iLogin;
	public ImageIcon iExit;
	public ImageIcon iList;
	public ImageIcon iSearch;
	public ImageIcon iBooking;
	public ImageIcon iCancel;
	public ImageIcon iMovieContent;
	public ImagePro() {
		try {
			BufferedImage biUsername = ImageIO.read(new File("sources/icon/username.png"));
			BufferedImage biPassword = ImageIO.read(new File("sources/icon/password.png"));
			BufferedImage biLogin = ImageIO.read(new File("sources/icon/login.png"));
			BufferedImage biExit = ImageIO.read(new File("sources/icon/exit.png"));
			BufferedImage biList = ImageIO.read(new File("sources/icon/list.png"));
			BufferedImage biSearch = ImageIO.read(new File("sources/icon/search.png"));
			BufferedImage biBooking = ImageIO.read(new File("sources/icon/booking.png"));
			BufferedImage biCancel = ImageIO.read(new File("sources/icon/cancel.png"));
			BufferedImage biMovieContent = ImageIO.read(new File("sources/image/movieBackground.jpg"));
			iUsername = new ImageIcon(biUsername.getScaledInstance(30, 30, biUsername.SCALE_SMOOTH));
			iPassword = new ImageIcon(biPassword.getScaledInstance(30, 30, biPassword.SCALE_SMOOTH));
			iLogin = new ImageIcon(biLogin.getScaledInstance(20, 20, biLogin.SCALE_SMOOTH));
			iExit = new ImageIcon(biExit.getScaledInstance(20, 20, biExit.SCALE_SMOOTH));
			iList = new ImageIcon(biList.getScaledInstance(30, 30, biList.SCALE_SMOOTH));
			iSearch = new ImageIcon(biSearch.getScaledInstance(30, 30, biSearch.SCALE_SMOOTH));
			iBooking = new ImageIcon(biBooking.getScaledInstance(30, 30, biBooking.SCALE_SMOOTH));
			iCancel = new ImageIcon(biCancel.getScaledInstance(30, 30, biCancel.SCALE_SMOOTH));
			iMovieContent = new ImageIcon(biMovieContent);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
