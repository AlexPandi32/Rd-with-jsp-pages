package ransomdetection;
import dao.Dao;
public class RansomDetection{  
   
	public static String lastTime;
 static {  System.out.println("Loading");
           System.loadLibrary("bmfrommysql"); 
           System.out.println("Loaded");
                                   
         }
    
 private void callback(String xml)
    {   
	 
	    Checker checker = new Checker();
        checker.add(xml);
    }
 
  private native void getEvents(String query);
    
  public  static String  collectEvents() {
	      
	        RansomDetection  rd = new RansomDetection();
	        Dao dao = new Dao();
	        String query =new String();
	        //String time= "0000-00-00T00:00:00.000Z";
            String time =dao.getTime();
	        query="Event/System[(EventID=4663) and TimeCreated[@SystemTime >='"+time+"']]";
	        rd.getEvents(query);
	        dao.updateTime(lastTime);
	 
	        
	   return "input";
		  
   }
   
}