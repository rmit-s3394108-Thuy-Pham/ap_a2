import java.io.*;
import java.util.*;

public class Adult extends Profile {

	private ArrayList<Profile> childrenlist = new ArrayList<>();
	private ArrayList<Profile> youngchildlist = new ArrayList<>();
	private ArrayList<Profile>  colleaguelist = new ArrayList<>();
	private ArrayList<Profile> classmateslist = new ArrayList<>();
	private Profile couple = null;
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
	public Profile getCouple()
	{
		return couple;
	}
	public void setCouple(Profile pro_partner)
	{
		this.couple = pro_partner;
	}
	public ArrayList<Profile> getChildrenList()
	{
		return childrenlist;
	}
	public void addChildren(Profile pro_children)
	{
		this.childrenlist.add(pro_children);
	}
	public ArrayList<Profile> getYoungChildList()
	{
		return youngchildlist;
	}
	public void addYoungChild(Profile pro_youngchild)
	{
		this.youngchildlist.add(pro_youngchild);
	}
	public ArrayList<Profile> getColleaguelist()
	{
		return colleaguelist;
	}
	public void addColleague(Profile pro_colleague)
	{
		this.colleaguelist.add(pro_colleague);
	}
	public ArrayList<Profile> getClassmatesList()
	{
		return classmateslist;
	}
	public void addClassmate(Profile pro_classmate)
	{
		this.classmateslist.add(pro_classmate);
	}
	//---------------------------------
	public void displayProfileInfo() {
		System.out.println("name: "+ getName());
		System.out.println("image: "+ getImage());
		System.out.println("status: "+ getStatus());
		System.out.println("age: "+ getAge());
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
