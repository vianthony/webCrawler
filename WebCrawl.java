import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class WebCrawl {
	
	//intializing final o/p lists
    ArrayList<String> success=new ArrayList<String>();
    ArrayList<String> error=new ArrayList<String>();
    ArrayList<String> skipped=new ArrayList<String>();
    String name="";
    //constructor to intialize the name
    public WebCrawl(String objName){
    	this.name=objName;
    }
    
    public static void main(String[] args)throws IOException {
    	String internet1="C:/Users/Anthony/Downloads/Anthony/java-workspace/WebCrawler/src/test1.txt";
        String internet2="C:/Users/Anthony/Downloads/Anthony/java-workspace/WebCrawler/src/test2.txt";
        //create webCrawl object
        WebCrawl wbObj=new WebCrawl(internet1);
        wbObj.checkLink();
        wbObj.display();
        System.out.println("*****************-****************************************");
        WebCrawl wbObj2=new WebCrawl(internet2);
        wbObj2.checkLink();
        wbObj2.display();
        
    	
    }
    
    public void checkLink()throws IOException{
    	
    	//load data from file        
        LoadData parData= new LoadData();
        HashMap<String, ArrayList<String>> hMap=parData.parseFile(name);             
        
        //iterate through the addresses in the hashmap
        Iterator i=  hMap.entrySet().iterator();        
        while(i.hasNext()){
            HashMap.Entry pair = (HashMap.Entry)i.next();
            String key=(String) pair.getKey();
            ArrayList<String> arr=hMap.get(key);
            
            //adds key address to success 
            if(!success.contains(key))
            	success.add(key);
            //adds key to skipped if already present in success
            else if(!skipped.contains(key))
            	skipped.add(key);
            
            	
            for(int j=0;j<arr.size();j++){
                String address=arr.get(j);
                //verfies link
                if(!hMap.containsKey(address))
                    error.add(address);
                //checks if is already added
                else if(!success.contains(address))
                    success.add(address);
              //adds link to skipped if already present in success
                else if(!skipped.contains(address))
                    skipped.add(address);
            }
        }       
        
    }
    
    //Display o/p
    public void display(){
    	System.out.println("For File:"+ name);
    	System.out.println("Success:");
    	System.out.println(success);
    	System.out.println("Skipped:");
    	System.out.println(skipped);
    	System.out.println("Error:");
        System.out.println(error);
        
    }
}
