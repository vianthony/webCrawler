import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class WebCrawlThread {
	
	ArrayList<String> success=new ArrayList<String>();
    ArrayList<String> error=new ArrayList<String>();
    ArrayList<String> skipped=new ArrayList<String>();
    HashMap<String, ArrayList<String>> hMap=new HashMap<String, ArrayList<String>>();

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		 String fileAddress="C:/Users/Anthony/Downloads/Anthony/java-workspace/WebCrawler/src/test2.txt";
		 WebCrawlThread obj=new WebCrawlThread();
		 obj.loadData(fileAddress);
	     
	     
	}
	public void loadData(String address)throws IOException{
		LoadData parData= new LoadData();
	    hMap=parData.parseFile(address);
	    Iterator i=  hMap.entrySet().iterator();
	    HashMap.Entry pair = (HashMap.Entry)i.next();
        String key=(String) pair.getKey();
        checkLink(key);
        System.out.println(success);
        System.out.println(error);
        System.out.println(skipped);
        return;
	}
	public void checkLink(String key)throws IOException{
		
		 if(!hMap.containsKey(key)&&success.contains(key))
			 error.add(key);
		 else{
			 if(!success.contains(key))
                 success.add(key);
             //adds link to skipped if already present in success
             else if(!skipped.contains(key))
                 skipped.add(key);
			 ArrayList<String> arr=hMap.get(key);
			 hMap.remove(key);
			 int i=0;
			 while(i<arr.size()){
				 checkLink(arr.get(i));
				 i++;
			 }
		 }		 
		 
		 return;			 
		 
	 }

}

