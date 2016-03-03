package matthew.huecon.huecomm;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import matthew.huecon.reference.Reference;
import matthew.huecon.util.URLReader;

public class HueStateReader {
	
	private static HueStateReader reader;
	private static String deviceIP;
	
	private static boolean isCreated = false;
	
	private HueStateReader(String ip){
		deviceIP = ip;
		isCreated = true;
	}
	
	/**
	 * Builds the class based on the given IP
	 */
	public static void create(String ip){
		if(!isCreated){
			reader = new HueStateReader(ip);
		}
		System.out.println("Instance of HueStateReader created.");
	}
	/**
	 * Returns an instance of this class
	 */
	public static HueStateReader getHueReader(){
		return reader;
	}
	/**
	 * Returns a boolean based on whether or an instance of the class has been created
	 */
	public static boolean hasBeenCreated(){
		return isCreated;
	}
	/**
	 * Used to obtain information from the hue bulb. Cast the output to the expected type
	 */
	public Object getProperty(String name, int light) throws Exception{
		Integer lightNumber = new Integer(light);
		try{
			String result = URLReader.read("http://"+deviceIP+
					Reference.BASE_URL+"lights/"+lightNumber+"/");
			
			JSONParser parser = new JSONParser();
			Object o = parser.parse(result);
			JSONObject state = (JSONObject)o;
			JSONObject on = (JSONObject) state.get("state");
			
			return on.get(name);
		}catch(ClassCastException e){
			System.err.println("Bulb number "+lightNumber+" is not registered with the bridge.");
			//TODO Throw my own error that doesn't close the program.
			System.exit(1);
			return false;
		}
	}
	/**
	 * Wrapper method. Returns a boolean representing whether light number 'light' is on
	 */
	public boolean isLightOn(int light) throws Exception{
		return (boolean) getProperty("on", light);
	}
	/**
	 * Wrapper method. Returns a long representing the hue of light number 'light'
	 */
	public long getHue(int light) throws Exception{
		return (long) getProperty("hue", light);
	}
	/**
	 * Wrapper method. Returns a long representing the brightness of light number 'light'
	 */
	public long getBrightness(int light) throws Exception{
		return (long) getProperty("bri", light);
	}
	/**
	 * Wrapper method. Returns a long representing the saturation of light number 'light'
	 * @return a long value refering to the bulb's saturation value
	 */
	public long getSaturation(int light) throws Exception{
		return (long) getProperty("sat", light);
	}
	
}
