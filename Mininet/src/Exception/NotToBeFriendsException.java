package Exception;

public class NotToBeFriendsException extends Exception
{
  public NotToBeFriendsException()
  {
    System.out.println("These two profiles cannot connect due to age gap");
  }
}
