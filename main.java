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
			JSONArray jarrHtml = new JSONArray();//creating a jsonArray to store each json Object
			Element etable = doc.select("tbody").first();//getting the contents of the table to be stored in the json Object
			Elements erow = etable.getElementsByTag("tr");       
			   for (int r = 0; r <erow.size(); r++) {
			        JSONObject jsoRecord = new JSONObject();
			        Elements ecols = erow.get(r).select("td");
			        String strTech = ecols.get(0).text();
			        String strReason = ecols.get(1).text();
			        String strLifecycle = ecols.get(2).text();
			        jsoRecord.put("Tech", strTech);
			        jsoRecord.put("Reason", strReason);
			        jsoRecord.put("Lifecycle", strLifecycle);
			        jarrHtml.put(jsoRecord);
		        }
			//Converting data for Build stack to json    
		    JSONArray jarrHtml1 = new JSONArray();
			Element etable1 = doc.select("tbody").get(1);
		    Elements erow1 = etable1.getElementsByTag("tr");       
				for (int r = 0;r < erow1.size(); r++) {
				   	JSONObject jsoRecord = new JSONObject();
			     	Elements ecols = erow1.get(r).select("td");
		            String Tech = ecols.get(0).text();
		            String Use = ecols.get(1).text();
			        String Reason = ecols.get(2).text();
		            String Lifecycle = ecols.get(3).text();
		            jsoRecord.put("Tech", Tech);
		            jsoRecord.put("Use", Use);
		            jsoRecord.put("Reason", Reason);
		            jsoRecord.put("Lifecycle", Lifecycle);
		            jarrHtml1.put(jsoRecord);
	        }
			//Converting data for Infrastructure to json
			JSONArray jarrHtml2 = new JSONArray();
		    Element etable2 = doc.select("tbody").get(1);
		    Elements erow2 = etable1.getElementsByTag("tr");       
		        for (int r = 0; r < erow2.size(); r++) {
		            JSONObject jsoRecord = new JSONObject();
		        	Elements ecols = erow2.get(r).select("td");
		        	String Tech = ecols.get(0).text();
		            String Use = ecols.get(1).text();
		            String Empty = ecols.get(2).text();
		            jsoRecord.put("Tech", Tech);
		            jsoRecord.put("Use", Use);
		            jsoRecord.put(" ", Empty);
		            jarrHtml2.put(jsoRecord);
	        }
					    
				  //Outputting resultant jsonArray
		     JSONObject stackjson = new JSONObject(); 
	         stackjson.put("Programmatic stack", jarrHtml);
	         stackjson.put("Build Stack", jarrHtml1);
	         stackjson.put("Infrastructure", jarrHtml2);
	         System.out.println(stackjson.toString().replace(",", "\n,"));
			    
			
		
		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
