import java.io.*;
import java.util.*;

public class ReadFile
{
  public static void main(String[] args)
  {
    String filePeople = "document/people.txt";
    String fileRelation = "document/relations.txt";
    Hashtable<Integer, ArrayList<String>> relations = new Hashtable<>();
    ArrayList<Profile> listofPros = new ArrayList<Profile>();
    try
    {
      FileReader f_read = new FileReader(fileRelation);
      BufferedReader b_read = new BufferedReader(f_read);
      String line;
      int i = 0;
      try
      {
        line = b_read.readLine();
        while((line != null) && (!line.equals("\n")))
        {
          String[] eachrelation = new String[3];
          ArrayList<String> relationValue = new ArrayList<>();
          eachrelation = line.split(",");
          for (int a = 0; a < eachrelation.length; a++)
          {
            relationValue.add(eachrelation[a]);
          }
          relations.put(i, relationValue);
          line = b_read.readLine();
          i = i + 1;
        }
      }
      catch(IOException e)
      {
        System.out.println(e);
      }
    }

    catch(FileNotFoundException fnfe)
    {
      System.out.println(fnfe.getMessage());// need to modify when have GUI
    }




    try
    {
      FileReader fr = new FileReader(filePeople);
      BufferedReader br = new BufferedReader(fr);
      String sCurrentLine;
      try
      {
        sCurrentLine = br.readLine();
        while((sCurrentLine != null) && (!sCurrentLine.equals("\n")))
        {
          String[] person_details = new String[6];
          person_details = sCurrentLine.split(",");
          String name = person_details[0].trim();
          String imagePath = person_details[1].trim();
          String status = person_details[2].trim();
          String gender = person_details[3].trim();
          int age = Integer.parseInt(person_details[4].trim());
          String postcode = person_details[5];
          if (age > 16)
          {
            Profile pro = new Adult(name, imagePath, status, gender, postcode, age);
            listofPros.add(pro);
          }
          else if (age > 2 && age <=16)
          {
            ArrayList<String> parentNameList = new ArrayList<>();
            for (Object key: relations.keySet())
            {
              ArrayList<String> relationInfor = relations.get(key);
              if ((relationInfor.get(2)).equals("parent") && (relationInfor.get(0)).equals(name))
              {
                parentNameList.add(relationInfor.get(1));
              }
              else if ((relationInfor.get(2)).equals("parent") && (relationInfor.get(1)).equals(name))
              {
                parentNameList.add(relationInfor.get(0));

              }
            }

            if (parentNameList.size() == 2)
            {
              ArrayList<Profile> ChildrenparentProfileList = new ArrayList<>();
              for (String parentname : parentNameList)
              {
                for (Profile eachpro : listofPros)
                {

                  if ((eachpro.getName()).equals(parentname))
                  {

                    ChildrenparentProfileList.add(eachpro);
                  }
                }
              }
              Profile pro = new Children(name, imagePath, status, gender, postcode, age, (Adult)(ChildrenparentProfileList.get(0)), (Adult)(ChildrenparentProfileList.get(1)));
              listofPros.add(pro);
            }
          }
          else
          {
            ArrayList<String> parentNameList = new ArrayList<>();
            for (Object key: relations.keySet())
            {
              ArrayList<String> relationInfor = relations.get(key);
              if ((relationInfor.get(2)).equals("parent") && (relationInfor.get(0)).equals(name))
              {
                parentNameList.add(relationInfor.get(1));
              }
              else if ((relationInfor.get(2)).equals("parent") && (relationInfor.get(1)).equals(name))
              {
                parentNameList.add(relationInfor.get(0));
              }
            }
            if (parentNameList.size() == 2)
            {
              ArrayList<Profile> parentProfileList = new ArrayList<>();
              for (String parentname : parentNameList)
              {
                for (Profile eachpro : listofPros)
                {
                  if ((eachpro.getName()).equals(parentname))
                  {
                    parentProfileList.add(eachpro);
                  }
                }
              }
              Profile pro = new YoungChild(name, imagePath, status, gender, postcode, age, (Adult)(parentProfileList.get(0)) , (Adult)(parentProfileList.get(1)));
              listofPros.add(pro);
            }
          }
          sCurrentLine = br.readLine();
        }
      }
      catch(IOException e)
      {
        System.out.println(e); //need to modify when have GUI
      }
    }
    catch(FileNotFoundException fnfe)
    {
      System.out.println(fnfe.getMessage());// need to modify when have GUI
    }

    for (Profile p: listofPros)
    {
      if (p instanceof YoungChild)
      {

      System.out.println(p.getName() +" |" + p.getImage() + " |" + p.getStatus() + " " + p.getGender() + " |" + p.getPostcode() + " ---> young child");
      }
      else if (p instanceof Children)
      {

      System.out.println(p.getName() +" |" + p.getImage() + " |" + p.getStatus() + " |" + p.getGender() + " |" + p.getPostcode() + "-----> children");
      }
      else if ( p instanceof Adult)
      {

      System.out.println(p.getName() +" |" + p.getImage() + " |" + p.getStatus() + " |" + p.getGender() + " |" + p.getPostcode() + "-----> adult" );
      }

    }




}
}
