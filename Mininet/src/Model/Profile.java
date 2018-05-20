package Model;

import java.io.*;
import java.util.*;

public abstract class Profile {
	private String name;
	private String image;
	private String status;
	private String gender;
	private String postcode;
	private ArrayList<Profile> friendList = new ArrayList<>();

	public Profile(String name, String image, String status, String gender, String postcode) {
		this.setName(name);
		this.setImage(image);
		this.setStatus(status);
		this.setGender(gender);
		this.setPostcode(postcode);
	}

	public void displayProfileInfo() {
		System.out.println("name: "+ getName());
		System.out.println("image: "+ getImage());
		System.out.println("status: "+ getStatus());
		System.out.println("gender:  " + getGender());
		System.out.println("postcode: " + getPostcode());
	}

	public void updaterProfileInfo(String name, String image, String status) {

		setName(name);
		setImage(image);
		setStatus(status);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}
	public String getGender()
	{
		return gender;
	}
	public void setPostcode(String postcode)
	{
		this.postcode = postcode;
	}
	public String getPostcode()
	{
		return postcode;
	}

	public ArrayList<Profile> getFriendList() {
		return friendList;
	}

	public void addFriend(Profile pro_friend) {
		this.friendList.add(pro_friend);
	}
	public abstract void addClassmate(Profile pro_classmate);


}
