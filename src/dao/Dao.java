package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import bean.Event;

public class Dao {
	private static final String DRIVER="com.mysql.cj.jdbc.Driver";  
	private static final String CONNECTION_URL="jdbc:mysql://127.0.0.1:3306/ransomlog";  
	private static final String USERNAME="root";  
	private static final String PASSWORD="root"; 
	private static  Connection con =null;
	
static{  
		try{  
		Class.forName(DRIVER);  
		con=DriverManager.getConnection(CONNECTION_URL,USERNAME,PASSWORD);  
		}catch(Exception e){e.printStackTrace();}  
	}  
	
		public static Connection getConnection(){
			
			return con;
		}
		

	public void storeinDb(Event evt){
		Connection con;
		try {
		con = getConnection();
		
	    String sql = "INSERT INTO event VALUES (?,?,?,?)";
	    PreparedStatement ps =  con.prepareStatement(sql);
	    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    //String tempTime=sdf.format(evt.getTime());
	    String tempTime=evt.getTime().toString();
	    
	    ps.setString(1,tempTime);
	    ps.setString(2,evt.getProcess());
		ps.setString(3,evt.getObject());
		ps.setString(4,evt.getXml());
	    ps.executeUpdate();
	
		
		}catch(Exception e)
			{
				e.printStackTrace();
			}
	}

	public String getTime(){
		Connection con;
	
		con = getConnection();
		ResultSet rs=null ;
		String time="0000-00-00T00:00:00.000Z";
		try {
	    String sql = "Select scannedtime FROM LASTSCANNEDTIME";
	    PreparedStatement ps =  con.prepareStatement(sql);
	    rs= ps.executeQuery();
	   if(rs.next())
	    {  
	        time=rs.getString("scannedtime");  
	    }
	  
	 }catch(Exception e)
			{    
		        e.printStackTrace();
			}
		  return time;
	}
  public void updateTime(String time)
  {
	  Connection con;
		try {
		con = getConnection();
		String sql=  "UPDATE lastscannedtime SET scannedtime=? WHERE sno=?";
		
	    PreparedStatement ps =  con.prepareStatement(sql);
	    
	    ps.setString(1,time);;
	    ps.setInt(2, 1);
	    ps.executeUpdate();
		}catch(Exception e)
		{
			e.printStackTrace();
	
		}
  }
		public ResultSet getReport()
		  {   
			
			ResultSet rs =null;
			  Connection con;
				try {
				con = getConnection();
				String sql=  "select * from event";
			    PreparedStatement ps =  con.prepareStatement(sql);
			    rs=ps.executeQuery();
			    
				}catch(Exception e)
				{
					e.printStackTrace();
			    }
				return rs;        
  }
		
		//to get user credentials list;
		public HashMap<String,String> getDataSet()
		{
			HashMap<String,String> dataset= new HashMap<String,String>();
			ResultSet rs = null;
			Connection con;
			
			try {
				con = getConnection();
				String sql = "SELECT * FROM CREDENTIALS";
			    PreparedStatement ps =  con.prepareStatement(sql);
				rs = ps.executeQuery();
				
				if (rs != null) {
					while (rs.next()) {
						dataset.put(rs.getString("username"), rs.getString("userpass"));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
			
			return dataset;
		}
		
		//to store credentials;
		public void storeData(String uname , String upass)
		{   
			
			try {
			    Connection conn=getConnection();
			    String sql = "INSERT INTO CREDENTIALS VALUES (?,?)";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, uname);
				ps.setString(2, upass);
			    ps.executeUpdate();
				
		
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			
		}		
}
