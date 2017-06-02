//
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class LoadData {
	
	public  HashMap<String, ArrayList<String>> parseFile (String fileAddress) throws IOException{
		
		JSONParser parser = new JSONParser();
		HashMap<String, ArrayList<String>> hMap = new HashMap<String, ArrayList<String>>();
		try { 
            Object obj = parser.parse(new FileReader(fileAddress)); 
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray array = (JSONArray) jsonObject.get("pages");    
            	
           // Iterator i= array.iterator();
            int i=0;
            while(i<array.size()){
            	JSONObject pgObject = (JSONObject) array.get(i);
            	String key=(String) pgObject.get("address");
            	 ArrayList<String> arrValues= new ArrayList<String>();
            	 JSONArray links = (JSONArray) pgObject.get("links");
                int k=0;
                while(k<links.size()){
                	String pgName = (String) links.get(k);
                	arrValues.add((String) pgName);
                	k++;
                }
               hMap.put(key, arrValues);
               i++;
            }
          //  System.out.println(hMap);
            
		}
		catch (Exception e) {
            e.printStackTrace();
        }
            
		return hMap;
	
	}
}

