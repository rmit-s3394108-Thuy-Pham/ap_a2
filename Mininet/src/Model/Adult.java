import java.io.*;
import java.util.*;

public class Adult extends Profile {

	private Children[] childrenlist;
	private YoungChild[] youngchildlist;
	private ArrayList<Profile>  colleaguelist;
	private ArrayList<Profile> classmateslist;
	private Profile couple;
	private int age;

	public Adult(String name, String image, String status, String gender, String postcode, int age) {
		super(name, image, status, gender, postcode);
		this.setAge(age);
		// TODO Auto-generated constructor stub
	}
	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}
	//---------------------------------
	public void displayProfileInfo() {
		System.out.println("name: "+ getName());
		System.out.println("image: "+ getImage());
		System.out.println("status: "+ getStatus());
		System.out.println("status: "+ getAge());
	}

	// public void showRelation() {
	// 	if(child != null) {
	// 		System.out.println(getName() + "'s child: " + child.getName());
	// 	}
	// 	if(child == null) {
	// 		System.out.println("This adult has no children");
	// 	}


	//---------------------------------
	// public Childrend getChild() {
	// 	return child;
	// }

	// public void setChild(Childrend child) {
	// 	this.child = child;
	// }



	}
