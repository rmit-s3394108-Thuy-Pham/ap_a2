package Model2;

public class YoungChild extends Profile // if change to Model should be extends Profile
{
  private Adult parent1;
  private Adult parent2;
  private int age;

  public YoungChild(String name, String image, String status, int age)
  {
    super(name, image, status);
    this.setAge(age);
  }

  public YoungChild(String name, String image, String status, Adult parent1, Adult parent2)
  {
    super(name, image, status);
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
	  return this.parent2;
  }
}
