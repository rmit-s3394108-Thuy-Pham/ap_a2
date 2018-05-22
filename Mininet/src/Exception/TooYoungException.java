package Exception;

public class TooYoungException extends Exception
{
  public TooYoungException()
  {
    System.out.println("Young child can not have friends in this network!");
  }
}
