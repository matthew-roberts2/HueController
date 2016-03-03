package matthew.huecon.huecomm;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;

import matthew.huecon.reference.Reference;

public class HueStateWriter {
	private static HueStateWriter writer;
	private static String deviceIP;
	
	private static boolean isCreated = false;
	
	private HueStateWriter(String ip){
		deviceIP = ip;
		isCreated = true;
	}
	
	/*
	 * Builds the class based on the given IP
	 */
	public static void create(String ip){
		if(!isCreated){
			writer = new HueStateWriter(ip);
		}
		System.out.println("Instance of HueStateWriter created.");
	}
	/*
	 * Returns an instance of this class
	 */
	public static HueStateWriter getHueWriter(){
		return writer;
	}
	/*
	 * Returns a boolean based on whether or an instance of the class has been created
	 */
	public static boolean hasBeenCreated(){
		return isCreated;
	}
	/*
	 * Used to obtain information from the hue bulb. Cast the output to the expected type
	 */
	@SuppressWarnings("unchecked")
	public Object setProperty(int light, String name, Object state) throws Exception{
		URL url;
		Integer lightNumber = new Integer(light);
		
		if(light == 0){
			url = new URL("http://"+deviceIP+Reference.BASE_URL+"groups/0/action/");
		}else{
			url = new URL("http://"+deviceIP+Reference.BASE_URL+"lights/"+lightNumber+"/state/");
		}
		
		try{
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("PUT");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream());
			
			JSONObject payload = new JSONObject();
			payload.put(name, state);
			output.write(payload.toString());
			output.flush();
			output.close();
			
			int responce = connection.getResponseCode();
			System.out.println(responce);
			return responce;
		}catch (java.net.UnknownHostException e){
			System.out.println("Hue host connection failed. Check Connection settings");
		    return -1;   
		}catch (Exception e){
			System.out.println("Generic error! "+ e);
			return -1;
		}
	}
	/*
	 * Wrapper method. Sets light number 'light' to on
	 */
	public void setLightOnOff(int light, boolean on) throws Exception{
		setProperty(light, "on", on);
	}
	/*
	 * Wrapper method. Returns a long representing the hue of light number 'light'
	 */
	public void setHue(int light, long hue) throws Exception{
		setProperty(light, "hue", hue);
	}
	/*
	 * Wrapper method. Returns a long representing the brightness of light number 'light'
	 */
	public void setBrightness(int light, long brightness) throws Exception{
		setProperty(light, "bri", brightness);
	}
	/*
	 * Wrapper method. Returns a long representing the saturation of light number 'light'
	 */
	public void setSaturation(int light, long saturation) throws Exception{
		setProperty(light, "sat", saturation);
	}
	
	public void setAllOff() throws Exception{
		setLightOnOff(0,false);
	}
	public void setAllOn() throws Exception{
		setLightOnOff(0,true);
	}
}
