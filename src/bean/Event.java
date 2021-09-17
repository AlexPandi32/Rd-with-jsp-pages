package bean;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Event {



	final static double ENTROPY_LIMIT=7;
	private LocalDateTime Time;
	private String Process;
	private String Object;
	private String Xml;
	
	public Event() {
		
	}
	public Event( LocalDateTime time,String process, String object ,String xml) {
		Time = time;
		Process = process;
		Object = object;
		Xml=xml;
	}


	public String getProcess() {
		return Process;
	}


	public String getObject() {
		return Object;
	}


	public LocalDateTime getTime() {
		return Time;
	}
	

	public String getXml() {
		return Xml;
	}
	public void setXml(String xml) {
		Xml = xml;
	}
	
	public boolean isFileEncrypted()
	{    
		 int charINT;
	     int countOfChars=0; 
	     double e = 0.0;
	     Map<Character, Integer> occ = new HashMap<>();
		 File file = new File(this.Object);
		 try { 
		  
			 BufferedReader br = new BufferedReader(new FileReader(file));
			
	    while ((charINT = br.read()) != -1) {
			    char ch = (char)charINT;
			    if (occ.containsKey(ch)) {
			      occ.put(ch, occ.get(ch) + 1);
			    } else {
			      occ.put(ch, 1);
			    }
			    ++countOfChars;
			  }
		
           br.close();
		
		  for (Map.Entry<Character, Integer> entry : occ.entrySet()) {
		    double p = (double) entry.getValue() / countOfChars;
		    e -=p*( Math.log(p) / Math.log(2));
		  }
		  System.out.println("___e="+e+"___");
		  } catch (IOException e1) {
				return false;
		}
		
		if(Double.compare( e ,Event.ENTROPY_LIMIT )<0)
		{
			return false;
		}
		else {
			
			return true;
		}
			
		
	}
	
	
	
}
