package Exception;

public class NoSuchAgeException extends Exception
{
  public NoSuchAgeException()
  {
    System.out.println("The age is too large or too small!!");
  }
}
