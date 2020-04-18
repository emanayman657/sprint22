package sprint1_files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseStatus;

//import com.google.gson.Gson;

@RestController 
public class UserController implements UserInterface{
			    //@Autowired
			    public userModel U;
			    public userView view;
			    public response res;
			    //@RequestMapping(value ="/register",method=RequestMethod.POST)
				    public response register(@RequestParam String email,@RequestParam String user,@RequestParam String pass)  {
			    	
			    	addData(user, email, pass);
			    	res=new response("successfully registered");
		        	return res;
				        /*if (email.contains("@") && email.indexOf("@") < email.indexOf(".")) {
				            if (!user.isEmpty()) {
				                if (pass.length() >= 6) {
				                     addData(user, email, pass);
				                } else {
				                	res=new response("this password is short");
				                	return res;
				                }
				            } else {
				            	res=new response("this is empty username");
			                	return res;
				            	
				            }
				        }
				        else {
				        	res=new response("this email is invalid");
			            	return res;
				            
				        }
				        res=new response("successfully registered");
			        	return res;	*/
				        
			
				  }
                 // @RequestMapping(value ="/login",method= RequestMethod.POST)
                  public response login(@RequestParam String username,@RequestParam String pass) {
                	  
                	  res=new response("Successfully signed in");
                      return res;
                      /*boolean found = false;
                      if (username.length() > 0) {
                          if (pass.length() >= 6) {
                              found = checkIf_user_is_exist(username, pass);
                              if (found) {
                            	  res=new response("Successfully signed in");
                                  return res;
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
                      }*/
                  }
                  @RequestMapping(value ="/getAllUsers",method=RequestMethod.POST)
                  public List<userModel> getAllUsers() throws ClassNotFoundException {
                     List<userModel> users = new ArrayList<userModel>();
                      users =getAlldata();
                      return users;
                  }
                  @RequestMapping(value ="/updateView",method=RequestMethod.POST)
                  public List<userModel> updateView() throws ClassNotFoundException {
                     List<userModel> users = new ArrayList<userModel>();
                      users = getAlldata();
                      return users;
                  }
                  public Connection connect() {
                  	
                      Connection con=null;
                      try{
                     Class.forName("com.mysql.cj.jdbc.Driver"); 
                      String host = "jdbc:mysql://localhost:3306/registration";
                      String Uname = "root";
                      String password = "12345678";
                      con = DriverManager.getConnection(host, Uname, password);
                      System.out.println(con);
                      
                      }
                      catch (SQLException e) {
                          System.out.println(e.getMessage());
                      } catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
                      return con;
                      
                  }
                  public List<userModel> getAlldata() {
                      List<userModel> allusers=new ArrayList<userModel>();
                      try {
                          Connection con = connect();
                          System.out.println(con);
                          Statement stmt = con.createStatement();
                          String Sql = "SELECT * FROM user";
                          ResultSet res = stmt.executeQuery(Sql);
                          while (res.next()) {
                              userModel u=new userModel();
                              u.setUserName(res.getString(1));
                              u.setEmail(res.getString(2));
                              u.setPassword(res.getString(3));
                              allusers.add(u);

                              
                          }
                      }
                      catch (SQLException e) {
                          System.out.println(e.getMessage());
                      }
                      return allusers;
                  }
                  public void addData(String user, String email, String pass)  {
                      Connection con=connect();
                      String Sql="INSERT INTO user VALUES('"+user+"','"+email+"','"+pass+"')";
                      try
                      {
                      Statement stmt = con.createStatement();
                      stmt.executeUpdate(Sql);
                      }
                      catch(SQLException e){
                          System.out.println(e.getMessage());
                      }
                  }
                  public boolean checkIf_user_is_exist(String username,String pass)
                  {
                      
                    boolean found=false;
                    try
                    {
                    Connection con=connect();
                    Statement stmt = con.createStatement();
                    String sql="SELECT * FROM user";
                    //System.out.println("1");
                    ResultSet res = stmt.executeQuery(sql);
                    while(res.next())
                    {
                        if(username.equals(res.getString("username")) && pass.equals(res.getString("password")))
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

                  

