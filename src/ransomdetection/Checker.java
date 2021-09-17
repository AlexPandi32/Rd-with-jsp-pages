package ransomdetection;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import org.xml.sax.InputSource;
import bean.Event;
import dao.Dao;
import ransomdetection.RansomDetection;
public  class Checker {
 
	   final static int MAX_COUNT_LIMIT=5;
	 
	public  void add(String xml) {  
		 
		 
		 InputSource input = new InputSource(new StringReader(xml));
    	 DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder;
       try {
         dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(input);
         doc.getDocumentElement().normalize();
         XPath xPath =  XPathFactory.newInstance().newXPath();
         
 //Compiling Xpath expression To get the required data from xml ;
         String expression1= "/Event/System/TimeCreated";	        
         Element node = (Element) xPath.compile(expression1).evaluate(doc, XPathConstants.NODE);
         String expression2= "/Event/EventData";	        
         Element node2 = (Element) xPath.compile(expression2).evaluate(doc, XPathConstants.NODE);
 //fetching required data from elements        
         String temp=node.getAttribute("SystemTime");
         RansomDetection.lastTime=temp;
   
         LocalDateTime time=  LocalDateTime.parse(temp.substring(0,temp.length()-1)).plusMinutes(330);
         String object= node2.getElementsByTagName("Data").item(6).getTextContent();
         String process= node2.getElementsByTagName("Data").item(11).getTextContent();
       
         Event evt = new Event(time,process,object,xml);
         //  if the file is not encrypted add method  will break here itself;
        
         if( !evt.isFileEncrypted()) { return; }
        
         VirtualDb  db =new VirtualDb();
        
  		if( db.addWithCaution(evt)  )
  		{   
  			
  			if(VirtualDb.dataSet1.get(evt.getProcess()).size() >= Checker.MAX_COUNT_LIMIT ) {
  				
  				
  				Dao dao=new Dao();
  				
  	        	if(VirtualDb.dataSet1.get(evt.getProcess()).size()== Checker.MAX_COUNT_LIMIT)
  	        	{
  	                Deque<Event>   que=new LinkedList<Event>();
  	  			    que=VirtualDb.dataSet1.get(process);
  	  			    Iterator<Event> it =que.descendingIterator();
  	  			    System.out.println("____________________________________________________________________");
  	  		    while(it.hasNext()) {
  	  				 Event e= it.next();
  	  		         dao.storeinDb(e);
   					 System.out.println(evt.getTime()+"Suspicious Action detected at:"+ e.getObject()+"    \n \nby :"+e.getProcess());
  	  		           }
  	  		      System.out.println("____________________________________________________________________");
  	  			   	}
  	        	else
  	        		{
  	        		
  	                dao.storeinDb(evt);
  	                System.out.println("____________________________________________________________________");
  	        		System.out.println(evt.getTime()+"-->Suspicious Action detected at:"+evt.getObject()+"------"+evt.getProcess());
  	        		System.out.println("____________________________________________________________________");
  	        		}
  	        	
  	            
  	            
  	            
  	        }
  	        	
  		}
         
       }catch (ParserConfigurationException e) {
           e.printStackTrace();
        } catch (SAXException e) {
           e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        } catch (XPathExpressionException e) {
        	e.printStackTrace();
        }	
	
}
	

}