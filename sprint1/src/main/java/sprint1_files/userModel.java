package sprint1_files;


/**
 *
 * @author Alaa_Ibrahim
 */

public class userModel {
    private String UserName;
    private String Email;
    private String Password;
    private String Type;

    //***********************************************
    public String getUserName()
    {
        return UserName;
    }

    public void setUserName(String name)
    {
        this.UserName = name;
    }
    //***********************************************
    public String getEmail()
    {
        return Email;
    }

    public void setEmail(String email)
    {
        this.Email = email;
    }
    //***********************************************
    public String getPassword()
    {
        return Password;
    }

    public void setPassword(String Pass)
    {
        this.Password = Pass;
    }
    public String getType()
    {
        return Type;
    }

    public void setType(String type)
    {
        this.Type = type;
    }
       
}

