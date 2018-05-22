package Exception;

public class NoParentException extends Exception
{
  public NoParentException(String type_error)
  {
    if (type_error.equals("delete parent error"))
    {
      System.out.println("Delete this account will affect its children account!!");
    }
    else if (type_error.equals("lack of 1 parent"))
    {
      System.out.println("Please enter both parents for this child!");
    }

  }
}
