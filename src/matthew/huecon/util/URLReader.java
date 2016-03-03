package matthew.huecon.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import matthew.huecon.reference.Reference;

public class URLReader {
	public static String read(String address){
		String nextLine;
		URL url;
		URLConnection connection;
		InputStreamReader input;
		BufferedReader buffered;
		try{
			url = new URL(address);
			connection = url.openConnection();
			input = new InputStreamReader(connection.getInputStream());
			buffered = new BufferedReader(input);
			nextLine = buffered.readLine();
			if(nextLine!=null){
				return nextLine;
			}
			return null;
		}catch(MalformedURLException e){
			System.err.println("URL malformed. Check it: "+e);
		}catch(IOException e){
			System.err.println("Connection error. Check your Internet settings: "+e);
		}
		return null;
	}
	
	public static String contactUPNPServer(){
		try{
			String r = read(Reference.UPNP);
			JSONParser parser = new JSONParser();
            Object obj = parser.parse(r);
            JSONArray array = (JSONArray) obj;
            JSONObject obj2 = (JSONObject)array.get(0);

            return (obj2.get("internalipaddress")).toString();
		}catch (IndexOutOfBoundsException e){
            System.out.println("Error fetching Bridge IP from Meethue.com UPNP service. Make sure the bridge is connected or "+Reference.UPNP+" unreachable"); //System.out.println(e);
            System.exit(1); // Terminate the application.
        }catch (Exception e){
            System.out.println("Generic Exception: "+e);
        }
		return null;
	}
}
