package Model2;

import java.util.*;
/*@author Thuy Pham*/

public abstract class Profile
{
  //common variables between users
  private String name;
  private String image;
  private String status;
  private ArrayList<Profile> listOfFriends = new ArrayList<>(); // list of friends for each user

  public Profile(String name, String image, String status)
  {
    this.setName(name);
    this.setImage(image);
    this.setStatus(status);
  }

  public String getName()
  {
    return name;
  }

  public String getImage()
  {
    return image;
  }

  public String getStatus()
  {
    return status;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public void setImage(String image)
  {
    this.image = image;
  }

  public void setStatus(String status)
  {
    this.status = status;
  }

  public ArrayList<Profile> getListofFriends()
  {
    return listOfFriends;
  }

  public void setListofFriends(ArrayList<Profile> listOfFriends)
  {
    this.listOfFriends = listOfFriends;
  }




}
