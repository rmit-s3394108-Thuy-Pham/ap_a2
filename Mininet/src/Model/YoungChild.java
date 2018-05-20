package Model;

import java.io.*;
import java.util.*;
public class YoungChild extends Profile
{
  private Adult parent1;
  private Adult parent2;
  private int age;

  public YoungChild(String name, String image, String status, String gender, String postcode, int age) {
	  super(name, image, status, gender, postcode);
	  this.age = age;
  }
  public YoungChild(String name, String image, String status, String gender, String postcode, int age, Adult parent1, Adult parent2)
  {
    this(name, image, status, gender, postcode, age);
    this.setAge(age);
    this.setParent1(parent1);
    this.setParent2(parent2);
  }

  public int getAge()
  {
    return age;
  }
  public void setAge(int age)
  {
    this.age = age;
  }

  public Adult getParent1()
  {
    return parent1;
  }
  public void setParent1(Adult parent1)
  {
    this.parent1 = parent1;
  }
  public void setParent2(Adult parent2)
  {
    this.parent2 = parent2;
  }
  public Adult getParent2()
  {
    return parent2;
  }
  public void addClassmate(Profile pro)
  {
    
  }
}
