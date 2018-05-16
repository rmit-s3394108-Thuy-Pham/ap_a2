import java.io.*;
import java.util.*;

public class ReadFile
{
  public static void main(String[] args) throws IOException
  {
    String filePeople = "document/people.txt";
    String fileRelation = "document/relation.txt";
    Hashtable<String, ArrayList<String>> hash_people = new Hashtable<>();
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
          String name = person_details[0];
          // String imagePath = person_details[1];
          // String status = person_details[2];
          // String gender = person_details[3];
          // int age = Integer.ParseInt(person_details[4]);
          // String postcode = person_details[5];
          ArrayList<String> details = new ArrayList<>();
          details.add(person_details[1]); // imagePath O;
          details.add(person_details[2]); // status 1;
          details.add(person_details[3]); // gender 2;
          details.add(person_details[4]); // age 3;
          details.add(person_details[5]); // postcode 4;
          hash_people.put(name, details);
        }
      }
      catch(IOException e)
      {
        System.out.println(e); //need to modify when have GUI
      }
    }
    catch(FileNotFoundException fnfe)
    {
      System.out.println(fnfe.getMessage())// need to modify when have GUI
    }
    Set setofNames = new HashSet();
    try
    {
      FileReader frR = new FileReader(fileRelation);
      BufferedReader brR = new BufferedReader(frR);
      String sCurrentLineR;
      try
      {
        sCurrentLineR = brR.readLine();
        while((sCurrentLineR != null) && (!sCurrentLineR.equals("\n")))
        {
          String[] relation_details = new String[3];
          relation_details = sCurrentLineR.split(",");
          if ((relation_details[2]).equals("friends"))
          {
            if (!setofNames.contains(relation_details[0]))
            {
              String name = relation_details[0];
              eachpersondetails = hash_people.get(name);
              String imagePath = eachpersondetails[0];
              String status = eachpersondetails[1];
              String gender = eachpersondetails[2];
              int age = Integer.ParseInt(eachpersondetails[3]);
              String postcode = eachpersondetails[4];
              if (age <= 16)
              {
                Profile pro = new Children()
              }
            }
          }
        }

      }
      catch(IOException e)
      {
        System.out.println(e); //need to modify when have GUI
      }
    }
    catch(FileNotFoundException fnfe)
    {
      System.out.println(fnfe.getMessage())// need to modify when have GUI
    }
}
