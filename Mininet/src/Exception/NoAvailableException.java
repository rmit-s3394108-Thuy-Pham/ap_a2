package Exception;

public class NoAvailableException extends Exception
{
  public NoAvailableException()
  {
    System.out.println("This is not available to be coupled!");
  }
}
