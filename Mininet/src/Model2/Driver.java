import java.util.*;
import java.util.InputMismatchException;
/*@author Thuy Pham*/


public class Driver
/*
This Driver class includes methods to
- display menu
- list all users
- select user by name
- check if two people are direct friend
- list all the relatives(parents or children) of the given user
- add new user
- close the program

This class also includes a SubMenu function to display a SubMenu for each user if user was selected by name.


*/
{

  ArrayList<User> userList = new ArrayList<>(); // list stored all the user's profiles


  // constructor Driver()
  public Driver()
  {
    createTestingData(); // method to create an array of User objects. These object could be Adult type or Dependent type
    int choice = 0;
    do
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("          MiniNet Menu                ");
        System.out.println("======================================");
        System.out.println("1.  List everyone                     ");
        System.out.println("2.  Select a person                   ");
        System.out.println("3.  Are these two direct friends?     ");
        System.out.println("4.  Does user have child/parents?");
        System.out.println("5.  Add a person                      ");
        System.out.println("6.  Exit                              ");
        System.out.println("Enter an option:");
        try
        {
          choice = sc.nextInt();
          if (choice !=1 && choice !=2 && choice !=3 && choice !=4 && choice !=5 && choice !=6)
          {
            System.out.println("Choice is out of range. Please try again.");
          }
        }
        catch(InputMismatchException error)
        {
          choice = 0;
          System.out.println("You must enter a number. Please try again."); // make sure the user enter correctly
        }

        switch(choice)
        {
          case 1:
          listAllUsers(); // method to list all users that exist in the network. User's profiles in this program are all stored in a list call "userList"
          break;
          case 2:
          selectUserByName(); // method to prompt user a name and then check if the name matches with any user's name in the userList
          break;
          case 3:
          isDirectFriend(); // method to check if two people are direct friends
          break;
          case 4:
          listAllUserRelatives(); // method to list parents/child of the input user
          break;
          case 5:
          addNewUser(); // method to add new user to the "userList"
          break;
          case 6:
          System.out.println("Bye Bye");
          break;
        }
      }while( choice != 6);
  }

  //fetching data for testing process

  public void createTestingData()
  {
    User u[] = new User[10];
    u[0] = new Adult("Alice", "alicePic.png", "working at KFC", 24);
    u[1] = new Adult("Bob", "bobPic.png", "student at RMIT", 26);
    u[2] = new Adult("Cathy", "cathyPic.png", "freelancer", 28);
    u[3] = new Adult("Don", "donPic.png", "lookingforjobs", 30);
    u[4] = new Dependent("Rick", "rickPic.png", "student in primary school", 8);
    u[5] = new Dependent("Morty", "mortyPic.png", "student in primary school", 9);
    u[6] = new Adult("Rick's Mom", "rickmom.png", "divorced mum", 30);
    u[7] = new Adult("Rick's Dad", "rickdad.png", "divorced dad", 31);
    u[8] = new Adult("Morty's Mom", "mortymom.png", "happy mum", 29);
    u[9] = new Adult("Morty's Mom2", "mortymom2.png", "another happy mum", 30);
    // add everyone to userList
    for (int i = 0; i<10; i++)
      {userList.add(u[i]);}

    //making some connections between two Dependents
    u[4].getListofFriends().add(u[5]);
    u[5].getListofFriends().add(u[4]);
    //making some connections between Adults
    u[0].getListofFriends().add(u[1]);
    u[1].getListofFriends().add(u[0]);
    u[0].getListofFriends().add(u[3]);
    u[3].getListofFriends().add(u[0]);

    //making some connections between parents and children
    u[6].getListofFriends().add(u[4]);
    u[7].getListofFriends().add(u[4]);
    u[4].getListofFriends().add(u[6]);
    u[4].getListofFriends().add(u[7]);
    u[6].getListofFriends().add(u[7]);
    u[7].getListofFriends().add(u[6]);
    u[5].getListofFriends().add(u[8]);
    u[5].getListofFriends().add(u[9]);
    u[8].getListofFriends().add(u[5]);
    u[9].getListofFriends().add(u[5]);
    u[8].getListofFriends().add(u[9]);
    u[9].getListofFriends().add(u[8]);

    //connect Dependents with their parents
    if (u[4] instanceof Dependent && u[6] instanceof Adult && u[7] instanceof Adult)
    {
      ((Dependent)u[4]).setParent1((Adult)u[6]);
      ((Dependent)u[4]).setParent2((Adult)u[7]);
      ((Adult)u[7]).setDepdendent((Dependent)u[4]);
      ((Adult)u[6]).setDepdendent((Dependent)u[4]);
    }

    if (u[5] instanceof Dependent && u[8] instanceof Adult && u[9] instanceof Adult)
    {
      ((Dependent)u[5]).setParent1((Adult)u[8]);
      ((Dependent)u[5]).setParent2((Adult)u[9]);
      ((Adult)u[9]).setDepdendent((Dependent)u[5]);
      ((Adult)u[8]).setDepdendent((Dependent)u[5]);

    }

  }

// method to display all User's profiles

  public void listAllUsers()
  {
    for(User u: userList) // for each User object in the "userlist"
    {
      System.out.println("  UserName: " + u.getName());
      System.out.println("  Profile Picture: " + u.getImage());
      System.out.println("  Status: " + u.getStatus());
        if (u instanceof Dependent)
        {
          System.out.println("  Age: " + ((Dependent)u).getAge());
        }
        else
        {
          System.out.println("  Age: " + ((Adult)u).getAge());
        }
      System.out.println("-----------------------------------");

      }
    }



//method selectUserByName()
    public void selectUserByName()
    {
      String inputName;
      Scanner sc = new Scanner(System.in);
      boolean exist = false;
      do {
        System.out.println("Enter a name: "); // ask input from the user
        inputName = sc.nextLine();
        if (doesUserExist(inputName) == true) // check if username really exist in the user list or not.
          {
            subMenu(returnObjectUsersbyInputName(inputName)); //If yes, display a subMenu for other functions: displayProfile, updateUserProfile, deleteUserProfile or connectwithFriend
            exist = true;
          }

        else
          {
            System.out.println("UserName does not exist. Please enter another name.");
          }
      } while (exist == false);

    }
    // create a sub menu for three methods : display, update, delete
    public void subMenu(User u)
    {

      int choice = 0;
      Scanner sc = new Scanner(System.in);
      do
      {
        System.out.println("*******SubMenu "+ " for " + u.getName() + "*********");
        System.out.println("1. Display user profile");
        System.out.println("2. Update user profile" );
        System.out.println("3. Delete user profile:");
        System.out.println("4. Connect profile with another profile");
        System.out.println("5. Go back to main menu");
        System.out.println("Please enter a choice for user " + u.getName());
        try
        {
          choice = sc.nextInt();
          if (choice!= 1 && choice !=2 && choice !=3 && choice !=4 && choice !=5)
          {
            System.out.println("Choice is out of range. Please enter again.");
          }

        }
        catch (InputMismatchException e)
        {
          System.out.println("Choice must be a number. Please enter again.");
        }
        switch(choice)
        {
          case 1:
          System.out.println("-------------------------");
          System.out.println( u.getName() +"'s Profile");

          displayProfile(u);
          break;
          case 2:
          updateUserProfile(u);
          break;
          case 3:
          deleteUserProfile(u);
          System.out.println("User profile was successfully deleted.");
          choice = 5;
          break;
          case 4:
          connectwithFriend(u);

          break;
          case 5:
          System.out.println("Exit SubMenu Successfully");
          break;
        }
      }while(choice != 5);

    }
    //method displayProfile()
    public void displayProfile(User u)
    {
      System.out.println("UserName: " + u.getName());
      System.out.println("Profile Picture: " + u.getImage());
      System.out.println("Status: " + u.getStatus());
        if (u instanceof Dependent)
        {
          System.out.println("Age: " + ((Dependent)u).getAge());
        }
          else
          {
            System.out.println("Age: " + ((Adult)u).getAge());
          }

      System.out.println("Friends in the network including: ");
      for (User u1: u.getListofFriends())
        {
          System.out.println(u1.getName());
        }
        System.out.println("-------------------------");
}


    //method updateUserProfile()
    public void updateUserProfile(User u)
    {
      Scanner sc = new Scanner(System.in);
      String name;
      String image;
      String status;
      int age;
      System.out.println("New Name: ");
      name = sc.nextLine();
      u.setName(name);
      System.out.println("New Image: ");
      image = sc.nextLine();
      u.setImage(image);
      System.out.println("New Status: ");
      status = sc.nextLine();
      u.setStatus(status);
      System.out.println("New Age: ");
      age = sc.nextInt();

      if (u instanceof Adult)
      {
        ((Adult)u).setAge(age);
      }
      else
      {
        ((Dependent)u).setAge(age);
      }

    }

    //method deleteUserProfile
    public void deleteUserProfile(User u)
    {
      userList.remove(u);
    }

    //method connectwithFriend()
    public void connectwithFriend(User u)
    {
      listAllUsers();
      boolean check = false;
      Scanner sc = new Scanner(System.in);
      String choiceOfFriend;
      while (check == false)
            {
            System.out.println("From the list of users above, choose the user that " + u.getName() + " wants to make a connection with: ");
            choiceOfFriend = sc.nextLine();
            try
            {
                  if (!(checkConnection(u, returnObjectUsersbyInputName(choiceOfFriend)))) //make sure they havent had a connection yet
                      {    if ((checkIfAdult(u.getName()))&& (checkIfAdult(choiceOfFriend))) // make sure they are both adult OR
                            {(u.getListofFriends()).add(returnObjectUsersbyInputName(choiceOfFriend));
                              ((returnObjectUsersbyInputName(choiceOfFriend)).getListofFriends()).add(u);
                                System.out.println("Friendship for "+ u.getName() + "was successfully established!" );
                              check = true;
                              break;
                            }
                          else if((!(checkIfAdult(u.getName())))&& (!(checkIfAdult(choiceOfFriend)))) // make sure they are both children
                            {(u.getListofFriends()).add(returnObjectUsersbyInputName(choiceOfFriend));
                              ((returnObjectUsersbyInputName(choiceOfFriend)).getListofFriends()).add(u);
                                System.out.println("Friendship for "+ u.getName() + "was successfully established!" );
                              check = true;
                              break;
                            }
                          else
                          {
                            System.out.println("Adult and children cannot be friends unless aldults are parents of children!"); // informing them if they aren't children
                            check = false;
                            break;
                          }
                      }
                  else
                  {
                    System.out.println("They are already friends!"); // informing them if a mutual connection is already exist
                    check = false;
                  }
              }
              catch (NullPointerException e)
              {
                System.out.println("The name that you entered does not exist in the list of user!"); // sometimes they could enter name that has not existed in the network as well..
              }


        }

    }

    //method isDirectFriend()
    public void isDirectFriend()
    {
      String name1;
      String name2;
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter the first username: ");
      name1 = sc.nextLine();
      System.out.println("Enter the second username: ");
      name2= sc.nextLine();
      if (doesUserExist(name1) && doesUserExist(name2)) // check if the provided names are real first
        {
          if (checkConnection(returnObjectUsersbyInputName(name1), returnObjectUsersbyInputName(name2)))
            System.out.println("These two are direct friends.");
          else
          System.out.println("These are NOT friends.");
        }
      else
      System.out.println("At least one of the provided usernames might not exist.");
    }

          //method checkConnection(): check if two User object exist in each other's friendlist or not

    public boolean checkConnection(User u1, User u2)
    {
       boolean checkUserExistInFriendlist = false;
       for (User u : u1.getListofFriends())
       {
        if (u.equals(u2))
        {
          checkUserExistInFriendlist = true;
          break;
        }
      }
      if (checkUserExistInFriendlist == true)
        return true;
      else
        return false;
    }

    //method doesUserExist() : check if User object exist in the userlist or not. Only need to provide username of the user to know
    public boolean doesUserExist(String name)
    {
      boolean exist = false;
      for (User u : userList)
      {
        if (name.equals(u.getName()))
          exist = true;
      }
      return exist;
    }

    //method returnObjectUser(String nameofUser)
    public User returnObjectUsersbyInputName(String nameofUser)
    {
      for (User u : userList)
      {
        if (nameofUser.equals(u.getName()))
        {
          return u;
        }
      }
      return null;

    }

    //method listUserRelatives()

    public void listAllUserRelatives()
    { listAllUsers();
    String input_name;
    Scanner sc = new Scanner(System.in);
    while (true)
      {
        System.out.println("Enter a username to check if they have relatives: ");
        input_name = sc.nextLine();
        if (doesUserExist(input_name) == false) // check if the User exist or not first
          {System.out.println("Username does not exist. Please try again");}
        else
        {
          if (returnObjectUsersbyInputName(input_name) instanceof Adult) // check if the User object is Adult type or Dependent type
          {
            if (((Adult)returnObjectUsersbyInputName(input_name)).getDependent() !=null)
              {System.out.println("Children of " + input_name +" is: ");
              System.out.println((((Adult)returnObjectUsersbyInputName(input_name)).getDependent()).getName());
              }
            else
            System.out.println( input_name+ " doesn't have any children!");
          }
          else // if user is dependent
          {
            System.out.println("Parents of " + input_name + " are: ");
            System.out.println((((Dependent)returnObjectUsersbyInputName(input_name)).getParent1()).getName());
            System.out.println((((Dependent)returnObjectUsersbyInputName(input_name)).getParent2()).getName());
          }
          break;
        }
      }

    }


    //method addNewUser()
    public void addNewUser()
    {
      String name;
      String image;
      String status;
      int age = 0;
      Scanner sc = new Scanner(System.in);
      while (true)
        {System.out.println("Enter the name user wish to be called in the network: ");
        name = sc.nextLine();
        if (!doesUserExist(name))
          break;
        else
          System.out.println("The name already existed in the network!");
        }


      System.out.println("Please provide link for user image: ");
      image = sc.nextLine();
      System.out.println("Please provide user's employment status: ");
      status = sc.nextLine();
      boolean check = false;
      while(check == false)
        {
             try
             {
               System.out.println("How old is the user?");
               age = sc.nextInt();
               check = true;
             }
             catch (InputMismatchException e)
             {
               System.out.println("Please enter a number");
               check = false;
               sc.next();
             }
          }

       if (age > 16) // if the User is Adult
         userList.add(new Adult(name, image, status, age));
       else
       {
         System.out.println("Please specify users' parents in the list of users below: ");
         listAllUsers();
         String name1;
         String name2;
         Scanner scanner2 = new Scanner(System.in);
         System.out.println("Enter the first guardian: ");
         name1 = scanner2.nextLine();
         System.out.println("Enter the second guardian: ");
         name2 = scanner2.nextLine();
         if (doesUserExist(name1) && doesUserExist(name2) && checkIfAdult(name1) && checkIfAdult(name2))
         {  userList.add(new Dependent(name, image, status, age, ((Adult)returnObjectUsersbyInputName(name1)), ((Adult)returnObjectUsersbyInputName(name2)))); // add new Dependent using the second constructor
           ((Adult)returnObjectUsersbyInputName(name1)).setDepdendent((Dependent)(userList.get(userList.size()-1))); // update Adult's depedent variables as well
           ((Adult)returnObjectUsersbyInputName(name2)).setDepdendent((Dependent)(userList.get(userList.size()-1)));
         }
       }

    }

    //method checkAdult()
    public boolean checkIfAdult(String name)
    {
      boolean check = false;
      for (User u : userList)
      {
        if (returnObjectUsersbyInputName(name) instanceof Adult)
          check = true;
        else
          check = false;

      }
      return check;

    }





  }
