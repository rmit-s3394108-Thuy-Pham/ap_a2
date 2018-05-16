import java.io.*;
import java.util.*;

public class ReadFile
{
  public static void main(String[] args) throws IOException
  {
    String filePeople = "document/people.txt";
    String fileRelation = "document/relation.txt";
    ArrayList<Profile> arrayofProfiles = new ArrayList<>();
    try
    {
      FileReader fr = new FileReader(filePeople);
      BufferedReader bf = new BufferedReader(fr);
      String sCurrentLine;
      try
      {
        sCurrentLine = br.readLine();
        while((sCurrentLine != null) && (!sCurrentLine.equals("\n")))
        {
          String[] person_details = new String[6];
          person_details = sCurrentLine.split(",");
          String name = person_details[0];
          String imagePath = person_details[1];
          String status = person_details[2];
          String gender = person_details[3];
          int age = Integer.ParseInt(person_details[4]);
          String postcode = person_details[5];
          if (age <= 2)
          {
            Profile pro = new YoungChild(name, imagePath, status, );
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
}
