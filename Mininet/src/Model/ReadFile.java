package Model;
import java.io.*;
import java.util.*;



public class ReadFile
{
  private ArrayList<Profile> listofPros;
  private ArrayList<Profile> listofRelations;
  public ReadFile() throws IOException, CustomFileNotFoundException
  {
    listofPros = new ArrayList<Profile>();
    String filePeople = "document/people.txt";
    String fileRelation = "document/relations.txt";
	 Hashtable<String, ArrayList<String>> adults = new Hashtable<>();
    Hashtable<String, ArrayList<String>> children = new Hashtable<>();
    Hashtable<String, ArrayList<String>> youngchild = new Hashtable<>();
    Hashtable<Integer, ArrayList<String>> relations = new Hashtable<>();

    ArrayList<Profile> listofAdult = new ArrayList<Profile>();
    ArrayList<Profile> listofChildren = new ArrayList<Profile>();
    ArrayList<Profile> listofYoungChild = new ArrayList<Profile>();
    Hashtable<String, Profile> hash_Pro = new Hashtable<>();
    /*Reading data from people.txt and relations.txt*/
    try
    {
      FileReader fr = new FileReader(filePeople);
      BufferedReader br = new BufferedReader(fr);
      String sCurrentLine;
      try
      {
        while(((sCurrentLine = br.readLine()) != null) && (!sCurrentLine.equals("\n")))
        {
          String[] person_details = new String[6];
          person_details = sCurrentLine.split(",");
          String name = person_details[0].trim();
          String imagePath = person_details[1].trim();
          String status = person_details[2].trim();
          String gender = person_details[3].trim();
          int age = Integer.parseInt(person_details[4].trim());
          String postcode = person_details[5].trim();
          if (age > 16)
          {
            ArrayList<String> adultinfor = new ArrayList<String>();
            adultinfor.add(imagePath);
            adultinfor.add(status);
            adultinfor.add(gender);
            adultinfor.add(Integer.toString(age));
            adultinfor.add(postcode);
            adults.put(name, adultinfor);
          }
          else if (age > 2 && age <= 16)
          {
            ArrayList<String> childreninfor = new ArrayList<String>();
            childreninfor.add(imagePath);
            childreninfor.add(status);
            childreninfor.add(gender);
            childreninfor.add(Integer.toString(age));
            childreninfor.add(postcode);
            children.put(name, childreninfor);
          }
          else
          {
            ArrayList<String> youngchildinfor = new ArrayList<String>();
            youngchildinfor.add(imagePath);
            youngchildinfor.add(status);
            youngchildinfor.add(gender);
            youngchildinfor.add(Integer.toString(age));
            youngchildinfor.add(postcode);
            youngchild.put(name, youngchildinfor);
          }

        }
        br.close();
      }
      catch(IOException e)
      {
        System.out.println(e); //need to modify when have GUI
      }

    }

    catch(FileNotFoundException fnfe){

      throw new CustomFileNotFoundException ();
    }

    try
    {
      FileReader f_read = new FileReader(fileRelation);
      BufferedReader b_read = new BufferedReader(f_read);
      int i = 0;
      String line;
      try
      {
        while(((line = b_read.readLine()) != null) && (!line.equals("\n")))
        {
          String[] eachrelation = new String[3];
          ArrayList<String> relationValue = new ArrayList<>();
          eachrelation = line.split(",");
          for (int a = 0; a < eachrelation.length; a++)
          {
            relationValue.add(eachrelation[a].trim());
          }
          relations.put(i, relationValue);
          i = i + 1;
        }
        b_read.close();
      }
      catch(IOException e)
      {
        System.out.println(e);
      }
    }

    catch(FileNotFoundException fnfe)
    {
      throw new CustomFileNotFoundException();
    }

    /*Creating adult profiles*/
    for (Object key: adults.keySet())
    {
        String name = key.toString();
        ArrayList<String> adultinfor = adults.get(key);
        String imagePath = adultinfor.get(0);
        String status = adultinfor.get(1);
        String gender = adultinfor.get(2);
        int age = Integer.parseInt(adultinfor.get(3));
        String postcode = adultinfor.get(4);
        Profile pro_adult = new Adult(name, imagePath, status, gender, postcode, age);
        listofAdult.add(pro_adult);
        hash_Pro.put(name, pro_adult);
    }

    /*Creating children profiles*/
    for (Object key: children.keySet())
    {
      String name = key.toString();
      ArrayList<String> childreninfor = children.get(key);
      String imagePath = childreninfor.get(0);
      String status = childreninfor.get(1);
      String gender = childreninfor.get(2);
      int age = Integer.parseInt(childreninfor.get(3));
      String postcode = childreninfor.get(4);
      ArrayList<String> parentNameList = new ArrayList<>();
      for (Object keyRe: relations.keySet())
      {
        ArrayList<String> relationInfor = relations.get(keyRe);
        if ((relationInfor.get(2)).equals("parent") && (relationInfor.get(0)).equals(name))
        {
          parentNameList.add(relationInfor.get(1));
        }
        else if ((relationInfor.get(2)).equals("parent") && (relationInfor.get(1)).equals(name))
        {
          parentNameList.add(relationInfor.get(0));
        }
      }
      /*Check if that child has valid parent relations information or not*/
      if (parentNameList.size() == 2)
      {
        ArrayList<Profile> parentProfileList = new ArrayList<>();
        for (String parentname : parentNameList)
        {
          for (Profile eachpro : listofAdult)
          {
            if ((eachpro.getName()).equals(parentname))
            {
              parentProfileList.add(eachpro);
            }
          }
        }
        Profile adult1 = parentProfileList.get(0);
        Profile adult2 = parentProfileList.get(1);
        Profile pro_children = new Children(name, imagePath, status, gender, postcode, age, (Adult)(adult1), (Adult)(adult2));
        listofChildren.add(pro_children);
        ((Adult)adult1).addChildren(pro_children);
        ((Adult)adult2).addChildren(pro_children);
        hash_Pro.put(name, pro_children);
      }
    }
    /*Creating YoungChild profiles*/
    for (Object key: youngchild.keySet())
    {
      String name = key.toString();
      ArrayList<String> youngchildinfor = youngchild.get(key);
      String imagePath = youngchildinfor.get(0);
      String status = youngchildinfor.get(1);
      String gender = youngchildinfor.get(2);
      int age = Integer.parseInt(youngchildinfor.get(3));
      String postcode = youngchildinfor.get(4);
      ArrayList<String> parentNameList = new ArrayList<>();
      for (Object keyRe: relations.keySet())
      {
        ArrayList<String> relationInfor = relations.get(keyRe);
        if ((relationInfor.get(2)).equals("parent") && (relationInfor.get(0)).equals(name))
        {
          parentNameList.add(relationInfor.get(1));
        }
        else if ((relationInfor.get(2)).equals("parent") && (relationInfor.get(1)).equals(name))
        {
          parentNameList.add(relationInfor.get(0));
        }
      }
      /*Check if that child has valid parent relations information or not*/
      if (parentNameList.size() == 2)
      {
        ArrayList<Profile> parentProfileList = new ArrayList<>();
        for (String parentname : parentNameList)
        {
          for (Profile eachpro : listofAdult)
          {
            if ((eachpro.getName()).equals(parentname))
            {
              parentProfileList.add(eachpro);
            }
          }
        }
        Profile adult1 = parentProfileList.get(0);
        Profile adult2 = parentProfileList.get(1);
        Profile pro_youngchild = new YoungChild(name, imagePath, status, gender, postcode, age, (Adult)(adult1), (Adult)(adult2));
        listofYoungChild.add(pro_youngchild);
        ((Adult)adult1).addYoungChild(pro_youngchild);
        ((Adult)adult2).addYoungChild(pro_youngchild);
        hash_Pro.put(name, pro_youngchild);

      }
    }

    /*Update all the relations*/
    for(Object key: hash_Pro.keySet())
    {
      Profile eachpro = hash_Pro.get(key);
      for (Object keyRe: relations.keySet())
      {
        ArrayList<String> relationInfor = relations.get(keyRe);

        if ((relationInfor.get(0)).equals(eachpro.getName()))
        {
          if ((relationInfor.get(2)).equals("friends"))
          {
            Profile pro_newfriend = hash_Pro.get(relationInfor.get(1));
            boolean alreadyFriend = false;
            for (Profile pro_friend : eachpro.getFriendList())
            {
              if ((pro_friend.getName()).equals(relationInfor.get(1)))
              {
                alreadyFriend = true;
              }
            }
            boolean notvalidFriendship = false;
            if (!(((eachpro instanceof Adult) && (pro_newfriend instanceof Adult)) || ((eachpro instanceof Children) && (pro_newfriend instanceof Children))))
            {
              notvalidFriendship =true;
            }

            if ((eachpro instanceof Children) && (pro_newfriend instanceof Children))
            {
              int age_gap = Math.abs(((Children)eachpro).getAge() - ((Children)pro_newfriend).getAge());
              if (age_gap > 3)
              {
                notvalidFriendship = true;
              }
            }
            if ((!alreadyFriend) && (!notvalidFriendship))
            {
              eachpro.addFriend(pro_newfriend);
              pro_newfriend.addFriend(eachpro);
            }
          }
          else if ((relationInfor.get(2)).equals("couple"))
          {
            Profile pro_newcouple = hash_Pro.get(relationInfor.get(1));
            boolean isAdult = false;
            if ((pro_newcouple instanceof Adult) && (eachpro instanceof Adult))
            {
              isAdult = true;
            }
            if(((((Adult)(eachpro)).getCouple()) == null) && isAdult)
            {
              ((Adult)eachpro).setCouple(pro_newcouple);
              ((Adult)pro_newcouple).setCouple(eachpro);
            }
          }
          else if ((relationInfor.get(2)).equals("colleagues"))
          {
            Profile pro_newColleague = hash_Pro.get(relationInfor.get(1));
            boolean alreadyColleague = false;
            boolean isAdult = false;
            if ((pro_newColleague instanceof Adult) && (eachpro instanceof Adult))
            {
              isAdult = true;
            }

            if (isAdult)
            {
              for (Profile pro_colleague : ((Adult)eachpro).getColleaguelist())
              {
                if ((pro_colleague.getName()).equals(relationInfor.get(1)))
                {
                  alreadyColleague = true;
                }
              }
            }

            if ((!alreadyColleague) && (isAdult))
            {
              ((Adult)eachpro).addColleague(pro_newColleague);
              ((Adult)pro_newColleague).addColleague(eachpro);
            }
          }
          else if ((relationInfor.get(2)).equals("classmates"))
          {
            Profile pro_newClassmate = hash_Pro.get(relationInfor.get(1));
            boolean alreadyClassmate = false;
            boolean belowTwoYearsOld = false;
            if (eachpro instanceof Adult)
            {
              for (Profile pro_classmate : ((Adult)eachpro).getClassmatesList())
              {
                if ((pro_classmate.getName()).equals(relationInfor.get(1)))
                {
                  alreadyClassmate = true;
                }
              }
            }
            if (eachpro instanceof Children)
            {
              for (Profile pro_classmate : ((Children)eachpro).getClassmatesList())
              {
                if ((pro_classmate.getName()).equals(relationInfor.get(1)))
                {
                  alreadyClassmate = true;
                }
              }
            }

            if ((eachpro instanceof YoungChild) || (pro_newClassmate instanceof YoungChild))
            {
              belowTwoYearsOld = true;
            }
            if ((!alreadyClassmate) && (!belowTwoYearsOld))
            {
              eachpro.addClassmate(pro_newClassmate);
              pro_newClassmate.addClassmate(eachpro);
            }
          }
        }
      }
    }

    for (Object key: hash_Pro.keySet())
    {
      listofPros.add(hash_Pro.get(key));
    }




    // System.out.println(adults);
    // System.out.println(children);
    // System.out.println(youngchild);
    // System.out.println(relations);


    // for (Profile p: listofPros)
    // {
    //   if (p instanceof YoungChild)
    //   {
    //   System.out.println(p.getName() +" |" + p.getImage() + " |" + p.getStatus() + " " + p.getGender() + " |" + p.getPostcode() + " ---> young child");
    //   System.out.println((((YoungChild)p).getParent1()).getName() + "--> this is parent1 of: " + p.getName());
    //   System.out.println((((YoungChild)p).getParent2()).getName() + "--> this is parent2 of: " + p.getName());
    //   }
    //   else if (p instanceof Children)
    //   {
    //   System.out.println(p.getName() +" |" + p.getImage() + " |" + p.getStatus() + " |" + p.getGender() + " |" + p.getPostcode() + "-----> children");
    //   System.out.println((((Children)p).getParent1()).getName() + "--> this is parent1 of: " + p.getName());
    //   System.out.println((((Children)p).getParent2()).getName() + "--> this is parent2 of: " + p.getName());
    //   }
    //   else if ( p instanceof Adult)
    //   {
    //     System.out.println(p.getName() +" |" + p.getImage() + " |" + p.getStatus() + " |" + p.getGender() + " |" + p.getPostcode() + "-----> adult" );
    //   if (((Adult)p).getCouple() != null)
    //   {
    //     System.out.println((((Adult)p).getCouple()).getName() + "--> this is couple of " + p.getName());
    //   }
    //   if (((Adult)p).getChildrenList().size() != 0)
    //   {
    //     for (int i = 0; i < (((Adult)p).getChildrenList()).size(); i++ )
    //     {
    //     System.out.println(((Adult)p).getChildrenList().get(i).getName() + " -->this is children of: " + p.getName());
    //     }
    //
    //   }
    //   }
    //
    // }


  }
  public ArrayList<Profile> getListofPros()
  {
    return this.listofPros;
  }
  public static void main(String args[]) throws IOException, CustomFileNotFoundException {
	  ReadFile reader = new ReadFile();
	  for(Profile pro: reader.getListofPros()) {
		  if(pro instanceof Adult) {
			  System.out.println("name: " + ((Adult)pro).getName());
			  System.out.println("Age: " + ((Adult)pro).getAge());
			  System.out.println("Status: " + ((Adult)pro).getImage());
			  System.out.println("Gender: " + ((Adult)pro).getStatus());
			  System.out.println("Postcode: " + ((Adult)pro).getGender());
		  }

		  if(pro instanceof Children) {
			  System.out.println("THis is a children");
		  }

		  if(pro instanceof YoungChild) {
			  System.out.println("this is a young child");
		  }
	  }
  }
//  public List<E>
public ArrayList<Profile> getListofRelations() {
	return listofRelations;
}
public void setListofRelations(ArrayList<Profile> listofRelations) {
	this.listofRelations = listofRelations;
}



}
