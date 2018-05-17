import java.io.*;
import java.util.*;

public class Children extends Profile {

	private Adult dad;
	private Adult mom;
	private Profile classmateslist;
	private int age;

	// public Childrend(String name, String image, String status, int age) {
	// 	super(name, image, status, gender, );
	// 	this.setAge(age);
	// 	// TODO Auto-generated constructor stub
	// }

	public Children(String name, String image, String status, String gender, String postcode, int age, Adult dad, Adult mom) {
		super(name, image, status, gender, postcode);
		this.setAge(age);
		this.setDad(dad);
		this.setMom(mom);
		// TODO Auto-generated constructor stub
	}
	//------------------------------------
	public void displayProfileInfo() {
		System.out.println("name: "+ getName());
		System.out.println("image: "+ getImage());
		System.out.println("status: "+ getStatus());
		System.out.println("status: "+ getAge());
	}

	public void showRelation() {
		if(dad != null) {
			System.out.println(getName() + "'s dad: " + dad.getName());
		}
		if(mom != null) {
			System.out.println(getName() + "'s mom: " + mom.getName());
		}
	}

	//----------------------------
	public Adult getDad() {
		return dad;
	}

	public void setDad(Adult dad) {
		this.dad = dad;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Adult getMom() {
		return mom;
	}

	public void setMom(Adult mom) {
		this.mom = mom;
	}

}
