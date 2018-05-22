package Model;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Menu {
	Scanner s;
	private Scanner sAdd;
	private Scanner sCheck;
	private Scanner sSearch;
	private ArrayList<Profile> profileList = new ArrayList<>();
	ReadFile readFile; 
	
	public Menu() throws CustomFileNotFoundException {
//		dataProvide();
		try {
			readFile = new ReadFile();
			profileList = readFile.getListofPros();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void listOfProfile() {
		for(Profile pro: profileList) {
			System.out.println((profileList.indexOf(pro) + 1 ) +"	name:"+ pro.getName());
			System.out.println("	image:"+ pro.getImage());
			System.out.println("	status:"+ pro.getStatus());
			if(pro instanceof Children) {
				System.out.println("	age:"+ ((Children)pro).getAge());
				System.out.println("	this is a Children");
			}
			
			if(pro instanceof Adult) {
				System.out.println("	age:"+ ((Adult)pro).getAge());
				System.out.println("	This is an Adult");
			}
			System.out.println("------------------------");
		}
	}
	//---------------------------------
	public void selectProfile() {
		String searchName;
		s = new Scanner(System.in);
		System.out.println("input a name:");
		searchName = s.nextLine();
		if(checkExistanceProfile(searchName) == true) {
			subMenu(objProReturn(searchName));
		}
		else
			System.out.println("this person isn't existed!!!");
		
	}
	
	public void subMenu(Profile pro) {
		int i = 0;
		s = new Scanner(System.in);
		do {
			System.out.println("----------------------");
			System.out.println("1. Display information");
			System.out.println("2. Update information");
			System.out.println("3. Delete information");
			System.out.println("4. Connect");
			System.out.println("5. Exit");
			
			try {
				i = s.nextInt();
			}catch(InputMismatchException e) {
				System.out.println("Wrong format!! please input a number!!");
			}
			
			switch(i) {
				case 1:
					displayProfileInfo(pro);
					break;
				case 2:
					updaterProfileInfo(pro);
					break;
				case 3:
					deleteProfileInfo(pro);
					break;
				case 4:
					connectFriend(pro);
					break;
				case 5:
					break;
				default:
					break;
			}
		}while(i != 5);
	}
	
	public void displayProfileInfo(Profile pro) {

		if(pro instanceof Children) {
			((Children)pro).displayProfileInfo();
		}
		if(pro instanceof Adult) {
			((Adult)pro).displayProfileInfo();
		}
	}
	
	public void updaterProfileInfo(Profile pro) {
		String name;
		String image;
		String status;
		s = new Scanner(System.in);
		System.out.println("name:");
		name = s.nextLine();
		System.out.println("image:");
		image = s.nextLine();
		System.out.println("status:");
		status = s.nextLine();
		
		pro.updaterProfileInfo(name, image, status);
		
		}
	
	public void deleteProfileInfo(Profile pro) {
//		profileList.remove(pro);
		for(Profile friend: (pro).getFriendList()) {
			friend.getFriendList().remove(pro);
		}
		if(pro instanceof Adult) {
			
			for(Profile child: ((Adult)pro).getChildrenList()) {
				if(((Children)child).getParent1().equals((Adult)pro)) {
					((Children)child).setParent1(null);
				}	
				if(((Children)child).getParent2().equals((Adult)pro)) {
					((Children)child).setParent2(null);
				}
			}
			
			for(Profile colleague: ((Adult)pro).getColleaguelist()) {
				((Adult)colleague).getColleaguelist().remove(pro);
			}
			for(Profile classmate: ((Adult)pro).getClassmatesList()) {
				((Adult)classmate).getClassmatesList().remove(pro);
			}
		}
		if(pro instanceof Children) {
			for(Profile classmate: ((Children)pro).getClassmatesList()) {
				if(classmate instanceof Adult) {
					((Adult)classmate).getClassmatesList().remove(pro);
				}
				if(classmate instanceof Children) {
					((Children)classmate).getClassmatesList().remove(pro);
				}
			}
			if(((Children)pro).getParent1() != null) {
				((Children)pro).getParent1().getChildrenList().remove(pro);
			}
			if(((Children)pro).getParent2() != null) {
				((Children)pro).getParent2().getChildrenList().remove(pro);
			}
		}
		if(pro instanceof YoungChild) {
			if(((YoungChild)pro).getParent1() != null){
				((YoungChild)pro).getParent1().getChildrenList().remove(pro);
			}
			if(((YoungChild)pro).getParent2() != null) {
				((YoungChild)pro).getParent2().getChildrenList().remove(pro);
			}
		}
		profileList.remove(pro);
	}
	
	//--------------------------------------------------------
	
	public void connectFriend(Profile pro) {
		String name;
		s = new Scanner(System.in);
		System.out.println("make friend with:");
		name = s.nextLine();
		if(checkExistanceProfile(name) == true ) {
			makeFriend(pro, objProReturn(name));
		}
		else
			System.out.println("The person you typed doesn't exist");
	}
	
	public void makeFriend(Profile pro1, Profile pro2) {
		
		if(pro1 instanceof Children && ((Children)pro1).getAge() <= 2) {
			System.out.println(pro1.getName()+ "'s age < 2 **** not enough to make friend");
		}
		if(pro2 instanceof Children && ((Children)pro2).getAge() <= 2) {
			System.out.println(pro2.getName()+ "'s age < 2 **** not enough to make friend");
		}
		
		
		
		if(pro1 instanceof Children && pro2 instanceof Children) {
			System.out.println("Gap:" + Math.abs(((Children)pro1).getAge() - ((Children)pro2).getAge()));
			if((Math.abs(((Children)pro1).getAge() - ((Children)pro2).getAge()) ) > 3) {
				System.out.println("The gap between ages greater than 3**** fail to make friend");
			}
			else 
				if(checkRelationship(pro1, pro2) == false && ((Children)pro1).getAge() > 2 && ((Children)pro2).getAge() > 2 && (Math.abs(((Children)pro1).getAge() - ((Children)pro2).getAge()) ) < 3 )  {
				pro1.getFriendList().add(pro2);
				pro2.getFriendList().add(pro1);
				}
		}
		if(pro1 instanceof Children && pro2 instanceof Adult) {
			if(checkRelationship(pro1, pro2) == false && ((Children)pro1).getAge() > 2 && checkPaternity((Adult)pro2, (Children)pro1) == false && (Math.abs(((Children)pro1).getAge() - ((Adult)pro2).getAge()) ) < 3) {
				pro1.getFriendList().add(pro2);
				pro2.getFriendList().add(pro1);
			}
			else
				System.out.println("Fail to make friend");
		}
		if(pro1 instanceof Adult && pro2 instanceof Children) {
			if(checkRelationship(pro1, pro2) == false && ((Children)pro2).getAge() > 2 && checkPaternity((Adult)pro1, (Children)pro2) == false && (Math.abs(((Adult)pro1).getAge() - ((Children)pro2).getAge()) ) < 3) {
				pro1.getFriendList().add(pro2);
				pro2.getFriendList().add(pro1);
			}
		}
		if(pro1 instanceof Adult && pro2 instanceof Adult) {
			if(checkRelationship(pro1, pro2) == false) {
				pro1.getFriendList().add(pro2);
				pro2.getFriendList().add(pro1);
			}
		}
		
	}
	//--------------------------------------------------------
	
	
	
	//--------------------------------------------------------
	public void addNewProfile() {
		String name;
		String image;
		String status;
		int age = 0;
		String dadName;
		String momName;
		s = new Scanner(System.in);
		sAdd = new Scanner(System.in);
		boolean checkAge = false;;
		
		while(true) {
			System.out.println("input name:");
			name = sAdd.nextLine();
			if(checkExistanceProfile(name) == false) {
				break;
			}
			else
				System.out.println("this person is existed!!! Please type another name!!");
			
		}
		System.out.println("input image:");
		image = sAdd.nextLine();
		System.out.println("input status:");
		status = sAdd.nextLine();
		System.out.println("input age:");
		try {
			age = sAdd.nextInt();
			checkAge = true;
		}catch(InputMismatchException e) {
			checkAge = false;
			System.out.println("**** Fail to add****the age should be a number!!*****");
		}
		
		if(checkAge == true && age > 16) {
			profileList.add(new Adult(name, image, status, age));
			System.out.println("a new Adult is created!!!!");
		}
		
		if(checkAge == true && age < 16) {
			System.out.println("Dad:");
			dadName = s.nextLine();
			System.out.println("Mom:");
			momName = s.nextLine();
			
			if(checkExistanceProfile(dadName) == false || checkExistanceProfile(momName) == false || ((Adult)objProReturn(dadName)).getChildrenList() != null || ((Adult)objProReturn(momName)).getChildrenList() != null) {
				System.out.println("***create false*****Dad or mom does'nt exist or has another child!****");
			}
			else {
				profileList.add(new Children(name, image, status, age, (Adult)objProReturn(dadName), (Adult)objProReturn(momName)));
				((Adult)objProReturn(dadName)).addChildren((Children)profileList.get(profileList.size()-1));;
				((Adult)objProReturn(momName)).addChildren((Children)profileList.get(profileList.size()-1));
				System.out.println("a new child is created!");
			}
		}
	}
	//---------------------------------------------------------
	public void addNewAdul(String name, String image, String status, String gender, int age, String postcode) {
		
	}
	
	//---------------------------------------------------------
	public void checkFriendRelation() {

		String ps1;
		String ps2;
		sCheck = new Scanner(System.in);
		listOfProfile();
		System.out.println("input the first person's name:");
		ps1 = sCheck.nextLine();
		System.out.println("input the second person's name:");
		ps2 = sCheck.nextLine();
		
		if(checkExistanceProfile(ps1) == true && checkExistanceProfile(ps2) == true) {
			if(checkRelationship(objProReturn(ps1), objProReturn(ps2)) == true) {
				System.out.println("they are friends!");
			}
			else
				System.out.println("they aren't friends!");
		}
		else
			System.out.println("typed person doesn't exist!");
		
	}
	//---------------------------------------------------------
	public void findOutRelation() {
		String searchName = "";
		sSearch = new Scanner(System.in);
		listOfProfile();
		
		System.out.println("input a name:");
			searchName = sSearch.nextLine();
		for(Profile pro: profileList) {
			if(searchName.equals(pro.getName())) {
				showRelation(pro);
			}
			 
		}
	}
	public void showRelation(Profile pro) {
		if(pro instanceof Children) {
//			((Children)pro).showRelation();
		}
		
		if(pro instanceof Adult) {
//			((Adult)pro).showRelation();
		}
	}
	
	//---------------------------------------------------------
	public boolean checkRelationship(Profile pro1, Profile pro2) {
		boolean ckValidFriend1 = false;
		boolean ckValidFriend2 = false;
		for(Profile pro: pro1.getFriendList()) {
			if(pro.equals(pro2)) {
				ckValidFriend1 = true;
				break;
			}
		}
		for(Profile pro: pro2.getFriendList()) {
			if(pro.equals(pro1)) {
				ckValidFriend2 = true;
				break;
			}
		}
		if(ckValidFriend1 == true && ckValidFriend2 == true) 
			return true;
		else 
			return false;
	}
	
	public boolean checkExistanceProfile(String name) {
		boolean check = false;
		for(Profile pro: profileList) {
			if(name.equals(pro.getName())) {
				check = true;
				break;
			}
		}
		return check;
	}
	
	public Profile objProReturn(String name) {
		for(Profile pro: profileList) {
			if(name.equals(pro.getName())) {
//				System.out.println("name correct!");
				return pro;
			}
		}
		
//		System.out.println("AHIHIHIHIH");
		return null;
	}
	
	
	public boolean checkPaternity(Adult adult, Children child) {
		if(adult.equals(child.getParent1()) || adult.equals(child.getParent2())) {
			return true;
		}
		else 
			return false;
	}
	public List<Profile> getProfileList(){
		return this.profileList;
	}
	//--------------------------------------------
	
	public boolean defineFriendRelations(Profile pro1, Profile pro2) {
		if(pro1 == pro2) {
			return false;
		}
		else {
			if(pro1 instanceof Adult && pro2 instanceof Adult) {
//				int i = 0;
				boolean check = true;
				for(Profile pro: pro1.getFriendList()) {
					if(pro.getName().equals(pro2.getName())) {
						check = false;
						break;
					}
				}
				for(Profile pro: pro2.getFriendList()) {
					if(pro.getName().equals(pro1.getName())) {
						check = false;
						break;
					}
				}
				if(check == true) {
					pro1.getFriendList().add(pro2);
					pro2.getFriendList().add(pro1);
					return true;
				}
			}
			if(pro1 instanceof Children && pro2 instanceof Children) {
//				int i = 0;
				boolean check = true;
				if(Math.abs(((Children)pro1).getAge() - ((Children)pro2).getAge()) >= 3) {
					check = false;
				}
				else {
					for(Profile pro: pro1.getFriendList()) {
						if(pro.getName().equals(pro2.getName())) {
							check = false;
							break;
						}
					}
					for(Profile pro: pro2.getFriendList()) {
						if(pro.getName().equals(pro1.getName())) {
							check = false;
							break;
						}
					}
				}
				if(check == true) {
					pro1.getFriendList().add(pro2);
					pro2.getFriendList().add(pro1);
					return true;
				}
				
			}
			return false;
		}
		
	}
	
	//--------------------------------------------
	public boolean defineColleaguelRelations(Profile pro1, Profile pro2) {
		if(pro1 == pro2) {
			return false;
		}
		else {
			if(pro1 instanceof Adult && pro2 instanceof Adult) {
				boolean check = true;
				for(Profile pro: ((Adult)pro1).getColleaguelist()) {
					if(pro.getName().equals(pro2.getName())) {
						check = false;
						break;
					}
				}
				for(Profile pro: ((Adult)pro2).getColleaguelist()) {
					if(pro.getName().equals(pro1.getName())) {
						check = false;
						break;
					}
				}
				if(check == true) {
					((Adult)pro1).getColleaguelist().add(pro2);
					((Adult)pro2).getColleaguelist().add(pro1);
					return true;
				}
			}
			return false;
		}
		
	}
	
	//--------------------------------------------
	public boolean defineClassmateRelations(Profile pro1, Profile pro2) {
		if(pro1 == pro2) {
			return false;
		}
		if(pro1 instanceof Adult && pro2 instanceof Adult) {
			boolean check = true;
			for(Profile pro: ((Adult)pro1).getClassmatesList()) {
				if(pro.getName().equals(pro2.getName())) {
					check = false;
					break;
				}
			}
			for(Profile pro: ((Adult)pro2).getClassmatesList()) {
				if(pro.getName().equals(pro1.getName())) {
					check = false;
					break;
				}
			}
			if(check == true) {
				((Adult)pro1).getClassmatesList().add(pro2);
				((Adult)pro2).getClassmatesList().add(pro1);
				return true;
			}
		}
		
		if(pro1 instanceof Children && pro2 instanceof Children) {
			boolean check = true;
			for(Profile pro: ((Children)pro1).getClassmatesList()) {
				if(pro.getName().equals(pro2.getName())) {
					check = false;
					break;
				}
			}
			for(Profile pro: ((Children)pro2).getClassmatesList()) {
				if(pro.getName().equals(pro1.getName())) {
					check = false;
					break;
				}
			}
			if(check == true) {
				((Children)pro1).getClassmatesList().add(pro2);
				((Children)pro2).getClassmatesList().add(pro1);
				return true;
			}
		}
		return false;
	}
	
	//--------------------------------------------
	
	public String checkRelationshipT(Profile pro1, Profile pro2)
	{
    String relationships = new String();
    ArrayList<Profile> friendlist;
    ArrayList<Profile> childrenlist;
    ArrayList<Profile> youngchildlist;
    ArrayList<Profile>  colleaguelist;
    ArrayList<Profile> classmateslist;
    Profile couple;
    String result;
    if (pro1 instanceof Adult)
    {
      friendlist = pro1.getFriendList();
      childrenlist = ((Adult)pro1).getChildrenList();
      youngchildlist = ((Adult)pro1).getYoungChildList();
      colleaguelist = ((Adult)pro1).getColleaguelist();
      classmateslist = ((Adult)pro1).getClassmatesList();
      couple = ((Adult)pro1).getCouple();
      if (couple != null)
      {
        if ((couple.getName()).equals(pro2.getName()))
        {
          relationships = relationships.concat(" in a couple relationship with");
        }
      }
      if (friendlist.size() > 0)
      {
        for (Profile eachpro: friendlist)
        {
          if ((eachpro.getName()).equals(pro2.getName()))
          {
            relationships = relationships.concat(" a friend");
          }
        }
      }
      if (childrenlist.size() > 0)
      {
        for(Profile eachpro : childrenlist)
        {
          if ((eachpro.getName()).equals(pro2.getName()))
          {
            relationships = relationships.concat(" a parent");
          }
        }
      }
      if (youngchildlist.size() > 0)
      {
        for(Profile eachpro : youngchildlist)
        {
          if ((eachpro.getName()).equals(pro2.getName()))
          {
            relationships = relationships.concat(" a parent");
          }
        }
      }
      if (colleaguelist.size() > 0)
      {
        for(Profile eachpro: colleaguelist)
        {
          if ((eachpro.getName()).equals(pro2.getName()))
          {
            relationships = relationships.concat(" a colleague");
          }
        }
      }
      if (classmateslist.size() > 0)
      {
        for(Profile eachpro: classmateslist)
        {
          if ((eachpro.getName()).equals(pro2.getName()))
          {
            relationships = relationships.concat(" a classmates");
          }
        }
      }
    }
    if (pro1 instanceof Children)
    {
      friendlist = pro1.getFriendList();
      classmateslist = ((Children)pro1).getClassmatesList();
      Adult parent1 = (((Children)pro1).getParent1());
      Adult parent2 = (((Children)pro1).getParent2());
      if (friendlist.size() > 0)
      {
        for (Profile eachpro: friendlist)
        {
          if ((eachpro.getName()).equals(pro2.getName()))
          {
            relationships = relationships.concat(" a friend");
          }
        }
      }
      if (classmateslist.size() > 0)
      {
        for(Profile eachpro: classmateslist)
        {
          if ((eachpro.getName()).equals(pro2.getName()))
          {
            relationships = relationships.concat(" a classmate");
          }

        }
      }
      if (((parent1.getName()).equals(pro2.getName())) || ((parent2.getName()).equals(pro2.getName())))
      {
        relationships = relationships.concat(" a children");
      }
      if (pro2 instanceof YoungChild)
      {
        Adult parentX = ((YoungChild)pro2).getParent1();
        Adult parentY = ((YoungChild)pro2).getParent1();
        if (((parentX.getName()).equals(parent1.getName())) || ((parentY.getName()).equals(parent1.getName())) || ((parentX.getName()).equals(parent2.getName())) || ((parentY.getName()).equals(parent2.getName())))
        {
          relationships = relationships.concat(" a sibling");
        }
      }
      else if (pro2 instanceof Children)
      {
        Adult parentX = ((Children)pro2).getParent1();
        Adult parentY = ((Children)pro2).getParent1();
        if (((parentX.getName()).equals(parent1.getName())) || ((parentY.getName()).equals(parent1.getName())) || ((parentX.getName()).equals(parent2.getName())) || ((parentY.getName()).equals(parent2.getName())))
        {
          relationships = relationships.concat(" a sibling");
        }
      }
    }
    if (pro1 instanceof YoungChild)
    {
      Adult parent1 = (((YoungChild)pro1).getParent1());
      Adult parent2 = (((YoungChild)pro1).getParent2());
      if (((parent1.getName()).equals(pro2.getName())) || ((parent2.getName()).equals(pro2.getName())))
      {
        relationships = relationships.concat(" a young child");
      }
      if (pro2 instanceof YoungChild)
      {
        Adult parentX = ((YoungChild)pro2).getParent1();
        Adult parentY = ((YoungChild)pro2).getParent1();
        if (((parentX.getName()).equals(parent1.getName())) || ((parentY.getName()).equals(parent1.getName())) || ((parentX.getName()).equals(parent2.getName())) || ((parentY.getName()).equals(parent2.getName())))
        {
          relationships = relationships.concat(" a sibling");
        }
      }
      else if (pro2 instanceof Children)
      {
        Adult parentX = ((Children)pro2).getParent1();
        Adult parentY = ((Children)pro2).getParent1();
        if (((parentX.getName()).equals(parent1.getName())) || ((parentY.getName()).equals(parent1.getName())) || ((parentX.getName()).equals(parent2.getName())) || ((parentY.getName()).equals(parent2.getName())))
        {
          relationships = relationships.concat(" a sibling");
        }
      }
    }
    if (!relationships.isEmpty())
    {
    result = pro1.getName() + " is " + relationships + " of " + pro2.getName();
    }
    else
    {
    result = "They do not have any relationships between them";
    }
    return result;
  }

	public ArrayList<Profile> findOutFamilyRelationT(Profile pro)
	{// family here means only parent-chidren relationship
		ArrayList<Profile> familylist = new ArrayList<Profile>();
		if(pro instanceof Adult)
		{
			ArrayList<Profile> youngchildlist = ((Adult)pro).getYoungChildList();
			ArrayList<Profile> childrenlist = ((Adult)pro).getChildrenList();
			if (youngchildlist.size() > 0)
			{
				for (Profile eachpro : youngchildlist)
				{
					familylist.add(eachpro);
				}
			}
			if (childrenlist.size() > 0)
			{
				for (Profile eachpro : childrenlist)
				{
					familylist.add(eachpro);
				}
			}
		}
		else if(pro instanceof Children)
		{
			Adult parent1 = ((Children)pro).getParent1();
			Adult parent2 = ((Children)pro).getParent2();
			familylist.add(parent1);
			familylist.add(parent2);
		}
		else
		{
			Adult parent1 = ((YoungChild)pro).getParent1();
			Adult parent2 = ((YoungChild)pro).getParent2();
			familylist.add(parent1);
			familylist.add(parent2);
		}
		return familylist;
	}
//	public void dataProvide() {
//	
//		Profile pro[] = new Profile[9];
//		pro[0] = new Children("Alex", "a.jpg", "i am Alex", 15);
//		pro[1] = new Adult("AlexDad", "a.jpg", "i am Alex dad", 40);
//		pro[2] = new Adult("AlexMom", "a.jpg", "i am Alex mom", 34);
//		pro[3] = new Children("Bill", "a.jpg", "i am Bill", 15);
//		pro[4] = new Adult("BillDad", "a.jpg", "i am Bill dad", 45);
//		pro[5] = new Adult("BillMom", "a.jpg", "i am Bill mom", 39);
//		pro[6] = new Adult("Steven", "a.jpg", "i am Steven", 30);
//		pro[7] = new Adult("Eliza", "a.jpg", "i am Eliza", 30);
//		pro[8] = new Adult("Alice", "a.jpg", "i am Alice", 30);
//
//		pro[0].getFriendList().add(pro[3]);
//		pro[3].getFriendList().add(pro[0]);
//		
//		pro[2].getFriendList().add(pro[6]);
//		pro[6].getFriendList().add(pro[2]);
//		
//		if(pro[0] instanceof Children && pro[1] instanceof Adult && pro[2] instanceof Adult){		
//			((Children)pro[0]).setParent1((Adult)pro[1]);
//			((Children)pro[0]).setParent1((Adult)pro[2]);
//			((Adult)pro[1]).addChildren((Children)pro[0]);
//			((Adult)pro[2]).addChildren((Children)pro[0]);
//		}
//		
//		if(pro[3] instanceof Children && pro[4] instanceof Adult && pro[5] instanceof Adult){		
////			((Children)pro[3]).setDad((Adult)pro[4]);
////			((Children)pro[3]).setMom((Adult)pro[5]);
////			((Adult)pro[4]).setChild((Children)pro[3]);
////			((Adult)pro[5]).setChild((Children)pro[3]);
//			((Children)pro[3]).setParent1((Adult)pro[4]);
//			((Children)pro[3]).setMom((Adult)pro[5]);
//			((Adult)pro[4]).setChild((Children)pro[3]);
//			((Adult)pro[5]).setChild((Children)pro[3]);
//		}
//		profileList.add(pro[0]);
//		profileList.add(pro[1]);
//		profileList.add(pro[2]);
//		profileList.add(pro[3]);
//		profileList.add(pro[4]);
//		profileList.add(pro[5]);
//		profileList.add(pro[6]);
//		profileList.add(pro[7]);
//		profileList.add(pro[8]);
//		
//	}
		
}




















