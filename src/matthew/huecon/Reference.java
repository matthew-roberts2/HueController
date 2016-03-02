package matthew.huecon;

public final class Reference {
	//Custom created user ID for accessing the lights
	public static final String USER_ID = "2b5465dd2824ea277605d993d1e3c43";
	//URL to Hue's developer access to retrieve HUE IP's from the network
	public static final String UPNP = "http://www.meethue.com/api/nupnp";
	//Base URL needed to access the lights. Append Bridge URL to the beginning.
	public static final String BASE_URL = "/api/"+USER_ID+"/";
	
}
