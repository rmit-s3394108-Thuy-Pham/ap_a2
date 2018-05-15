public class Adult extends User
/*@author Thuy Pham*/
{
  private Dependent dependent;
  private int age;


  public Adult(String name, String image, String status, int age)
  {
    super(name, image, status);
    this.setAge(age);
  }

  public int getAge()
  {
    return age;
  }

  public void setAge(int age)
  {
    this.age = age;
  }

  public Dependent getDependent()
  {
    return dependent;
  }

  public void setDepdendent(Dependent dependent)
  {
    this.dependent = dependent;
  }


}
