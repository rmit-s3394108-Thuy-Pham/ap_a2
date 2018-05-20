package Model;

import java.io.*;
import java.util.*;

public class Children extends Profile {

	private Adult parent1;
	private Adult parent2;
	private ArrayList<Profile> classmateslist = new ArrayList<>();
	private int age;
	public Children(String name, String image, String status, String gender, String postcode, int age) {
		super(name, image, status, gender, postcode);
		this.age = age;
	}
	public Children(String name, String image, String status, String gender, String postcode, int age, Adult adult1, Adult adult2) {
		this(name, image, status, gender, postcode, age);
		this.setAge(age);
		this.setParent1(adult1);
		this.setParent2(adult2);
	}
	public Adult getParent1() {
		return parent1;
	}

	public void setParent1(Adult adult1) {
		this.parent1 = adult1;
	}
	public Adult getParent2() {
		return parent2;
	}

	public void setParent2(Adult adult2) {
		this.parent2 = adult2;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public ArrayList<Profile> getClassmatesList()
	{
		return classmateslist;
	}
	public void addClassmate(Profile pro_classmate)
	{
		this.classmateslist.add(pro_classmate);
	}

	//------------------------------------
	// public void displayProfileInfo() {
	// 	System.out.println("name: "+ getName());
	// 	System.out.println("image: "+ getImage());
	// 	System.out.println("status: "+ getStatus());
	// 	System.out.println("status: "+ getAge());
	// }

	// public void showRelation() {
	// 	if(dad != null) {
	// 		System.out.println(getName() + "'s dad: " + dad.getName());
	// 	}
	// 	if(mom != null) {
	// 		System.out.println(getName() + "'s mom: " + mom.getName());
	// 	}
	// }

	//----------------------------

}
