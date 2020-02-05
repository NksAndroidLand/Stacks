import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 
 */

/**
 * @author Nkhenso Baloyi
 *
 */
public class main {

	/**
	 * @param args
	 */
	
	
	
	public static void main(String[] args) throws IOException {
		
		URL url;
		try {
			
			url = new URL("https://github.com/egis/handbook/blob/master/Tech-Stack.md");//Creating a url object and opening it through a scanner
			Scanner s = new Scanner(url.openStream());
			
			
			StringBuilder sb = new StringBuilder();
			while(s.hasNext()){
				sb.append(s.nextLine()).append("\n");//Using the stringbuilder object to store html code and appending each line
			}
			//Converting data for Programmatic stack to json
			Document doc = Jsoup.parse(sb.toString());//parsing the string into the Jsoup library to be read as html 
				JSONArray jArray = new JSONArray();//creating a jsonArray to store each json Object
			    Element table = doc.select("tbody").first();//getting the contents of the table to be stored in the json Object
			    Elements row = table.getElementsByTag("tr");       
			        for (int r = 0, c = row.size(); r < c; r++) {
			        	JSONObject jsonObject = new JSONObject();
			        	Elements cols = row.get(r).select("td");
			            String Tech = cols.get(0).text();
			            String Reason = cols.get(1).text();
			            String Lifecycle = cols.get(2).text();
			            jsonObject.put("Tech", Tech);
			            jsonObject.put("Reason", Reason);
			            jsonObject.put("Lifecycle", Lifecycle);
			            jArray.put(jsonObject);
			        }
			      //Converting data for Build stack to json    
			        JSONArray jArray1 = new JSONArray();
				    Element table1 = doc.select("tbody").get(1);
				    Elements row1 = table1.getElementsByTag("tr");       
				        for (int r = 0, c = row1.size(); r < c; r++) {
				        	JSONObject jsonObject = new JSONObject();
				        	Elements cols = row1.get(r).select("td");
				            String Tech = cols.get(0).text();
				            String Use = cols.get(1).text();
				            String Reason = cols.get(2).text();
				            String Lifecycle = cols.get(3).text();
				            jsonObject.put("Tech", Tech);
				            jsonObject.put("Use", Use);
				            jsonObject.put("Reason", Reason);
				            jsonObject.put("Lifecycle", Lifecycle);
				            jArray1.put(jsonObject);
				        }
				   //Converting data for Infrastructure to json
				        JSONArray jArray2 = new JSONArray();
					    Element table2 = doc.select("tbody").get(1);
					    Elements row2 = table1.getElementsByTag("tr");       
					        for (int r = 0, c = row2.size(); r < c; r++) {
					        	JSONObject jsonObject = new JSONObject();
					        	Elements cols = row2.get(r).select("td");
					            String Tech = cols.get(0).text();
					            String Use = cols.get(1).text();
					            String Empty = cols.get(2).text();
					            jsonObject.put("Tech", Tech);
					            jsonObject.put("Use", Use);
					            jsonObject.put(" ", Empty);
					            jArray2.put(jsonObject);
					        }
					    
				  //Outputting resultant jsonArray
			        JSONObject stackjson = new JSONObject(); 
			        stackjson.put("Programmatic stack", jArray);
			        stackjson.put("Build Stack", jArray1);
			        stackjson.put("Infrastructure", jArray2);
			        System.out.println(stackjson.toString().replace(",", "\n,"));
			    
			
		
		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
