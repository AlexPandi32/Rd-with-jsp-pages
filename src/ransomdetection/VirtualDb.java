package ransomdetection;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import bean.Event;

public class VirtualDb {

	
	private static boolean isaddedNew;
	final static int TIME_GAP_IN_SEC=300;  //5minutes;
	static HashMap<String,Deque<Event>>   dataSet1=new  HashMap<String,Deque<Event>>();
	 
	
	
	boolean addWithCaution(Event evt)
	{   
		
		VirtualDb.isaddedNew=false;
	   if(VirtualDb.dataSet1.containsKey(evt.getProcess())) {
		    
		     this.trim(evt); 
		     this.add(evt);
		}
	   
	   else
	   {
		   this.add(evt);
	   }
	    
		
	   return VirtualDb.isaddedNew;
	}
	
	public void trim(Event evt)
	{
		LocalDateTime now = LocalDateTime.now();
		String process=evt.getProcess();
		Deque<Event>   que=new LinkedList<Event>();
		
		Event tevt;
		que=VirtualDb.dataSet1.get(process);
	
		while(  (que.peekLast()!=null) &&  (Duration.between(que.peekLast().getTime(),evt.getTime()).getSeconds() >=TIME_GAP_IN_SEC)  )
		{    
			
			   
			   tevt=que.pollLast();
			   
	          
	         System.out.println("Trimmed");
		}
		if(que==null)
		{  
			VirtualDb.dataSet1.remove(process);
		}
		else
		{
	      VirtualDb.dataSet1.put(process,que);
		}
		
		
	}
	
	
	
	
	
	public void add(Event evt)
	{
	    String process=evt.getProcess();
		String object=evt.getObject();
		Deque<Event>   que=new LinkedList<Event>();
		
		if(VirtualDb.dataSet1.containsKey(process))
		{     
			  boolean  TO_ADD_EXISTING_QUEUE = true;
			  que=VirtualDb.dataSet1.get(process);
			  
			  for(Event e:que)
			  {    
				  if(e.getObject().equals(object)) {
					  TO_ADD_EXISTING_QUEUE=false;
					
					  break;
				  }
			  }
			
			if(TO_ADD_EXISTING_QUEUE)
			{  
				System.out.println("Added existing");
				que=VirtualDb.dataSet1.get(process);
				que.offerFirst(evt);
				VirtualDb.dataSet1.put(process,que );
				VirtualDb.isaddedNew=true;
			}
		}
		else
		{
			System.out.println("Added new");
			que.add(evt);
		    VirtualDb.dataSet1.put(process,que );
			VirtualDb.isaddedNew=true;
		}
		
	}
	
}
