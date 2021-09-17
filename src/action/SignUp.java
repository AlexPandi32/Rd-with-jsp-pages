package action;

import java.util.HashMap;

import com.opensymphony.xwork2.ActionSupport; 
import dao.Dao;

public class SignUp extends  ActionSupport {
	 
		
	    private String userName;
	    private String password;
	 
	    public String execute() throws Exception {
	    	
	    	Dao dao_Obj = new Dao();
	    	dao_Obj.storeData(userName, password);
	    	
	    	return "success";
	    	
	    }
	 
	    public void validate() {
	    	
	    	Dao dao_Obj = new Dao();
	    	HashMap<String,String> dataset ;
	    	 if (userName.length() == 0)  {
		            addFieldError("userName", "UserName.required");
		        } 
		        else if (password.length() == 0) {
		            addFieldError("password", getText("password.required"));
		       }
		        else if(dao_Obj.getDataSet().containsKey(userName))
	        {    
	        	addFieldError("userName", "UserName.alreadyExists choose another one");
	        }
	       
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
	 
	  
	}


