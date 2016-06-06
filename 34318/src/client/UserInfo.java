package client;

public class UserInfo {
	private String firstName;
    private String lastName;
    private String username;
    private String password;
	
    //first name
    public String setFirstName(String firstName)
    {
       return this.firstName = firstName;
    }
    public String getFirstName()
    {
       return firstName;
    }

    //last name
    public String setLastName(String lastName)
    {
       return this.lastName = lastName;
    }
    public String getLastName()
    {
       return lastName;
    }

   //username
    public String setUserName(String name)
    {
       return this.username = name;
    }
    public String getUserName()
    {
       return username;
    }

    //password
    public String setPassword(String password)
    {
       return this.password = password;
    }
    public String getPassword()
    {
       return password;
    }


}
