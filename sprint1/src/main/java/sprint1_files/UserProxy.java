package sprint1_files;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class UserProxy implements UserInterface{
	public UserController realUserController = new UserController();
	 public userModel U = new userModel();
	 public response res;
	@Override
	@RequestMapping(value ="/register",method=RequestMethod.POST)
	public response register(@RequestParam String email, @RequestParam String user, @RequestParam String pass)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		//realUserController.register(email,user,pass);
		if (email.contains("@") && email.indexOf("@") < email.indexOf(".")) {
           if (!user.isEmpty()) {
               if (pass.length() >= 6) {
                   //U=new userModel();
            	   boolean check;
            	   check=checkIf_user_is_exist(email,user,pass);
            	   if(check==false)
            	   {
            		   res=realUserController.register(email,user,pass);
            	   }
            	   else
            	   {
            		   res=new response("you already have account");
                      	return res;
            	   }
               } else {
            	   res=new response("this password is short");
               	return res;
               }
           } else {
        	   res=new response("this is empty username");
              	return res;
           }
       } else {
    	   res=new response("this email is invalid");
         	return res;
       }
		return res;
		
	}

	@Override
	 @RequestMapping(value ="/login",method= RequestMethod.POST)
	public response login(@RequestParam String username,@RequestParam String pass) {
		//realUserController.sign_in(username, pass);
		 boolean found = false;
        if (username.length() > 0) {
            if (pass.length() >= 6) {
                found = realUserController.checkIf_user_is_exist(username, pass);
                if (found) {
               	 res=realUserController.login(username, pass);
                } else {
                	res=new response("incorrect username or password");
                  	return res;
                }
            }
            else
            {
            	res=new response("this is short password");
              	return res;
            }
                
        }
        else
        {
        	res=new response("this is empty username");
          	return res;
        }
        return res;
	}
	public boolean checkIf_user_is_exist(String email,String username,String pass)
    {
        
      boolean found=false;
      try
      {
      Connection con=realUserController.connect();
      Statement stmt = con.createStatement();
      String sql="SELECT * FROM user";
      //System.out.println("1");
      ResultSet res = stmt.executeQuery(sql);
      while(res.next())
      {
          if(email.equals(res.getString("email"))&&username.equals(res.getString("username")) && pass.equals(res.getString("password")))
          {
              found=true;
              break;
          }
      }
      }
      catch(Exception e)
      {
          e.getMessage();
      }
      return found;
    }

}
