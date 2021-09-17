package action;


import java.util.HashMap;

import com.opensymphony.xwork2.ActionSupport; 
import dao.Dao;
public class Login extends ActionSupport  {
 
	
    private String userName;
    private String password;
 
    public String execute() throws Exception {
    	
    	
    	Dao dao_Obj = new Dao();
    	HashMap<String,String> dataset =new HashMap<String,String>();
    	dataset=dao_Obj.getDataSet();
    	
    	if(dataset.containsKey(userName))
    	{
    		if(dataset.get(userName).equals(password))
    		{
    			return "success";
    		}
    	}
    	
    	return "failure";
    	
    }
  
 
   
    public String getUserName() {
        return userName;
    }
 
    public void setUserName(String userName) {
        this.userName = userName;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }

	  public void validate() {
	        if (userName.length() == 0) {
	            addFieldError("userName", "UserName.required");
	        } else if (password.length() == 0) {
	            addFieldError("password", getText("password.required"));
	        }
	    } 
  
}
