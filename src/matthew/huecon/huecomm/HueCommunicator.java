package matthew.huecon.huecomm;

import matthew.huecon.huecomm.IPStatusEnum;
import matthew.huecon.util.HueIP;
import matthew.huecon.util.HueTimer;
import matthew.huecon.util.URLReader;

public class HueCommunicator {
	
	private static boolean is_created = false;
	
	private static HueCommunicator hue_communicator;
	private static HueBridge hue_bridge;
	private static HueStateReader hue_state_reader;
	private static HueStateWriter hue_state_writer;
	
	private HueCommunicator(IPStatusEnum function, String argument){
		switch (function){
		case USE_UPNP:
			HueBridge.create(URLReader.contactUPNPServer());
			break;
		case USE_COMMAND:
			HueBridge.create("10.206.194.13");
			break;
		case USE_PREVIOUS:
			HueBridge.create("10.206.194.13");
			//TODO Implement this USE_PREVIOUS.
			break;
		default:
			System.out.println("No idea what happened there...");
			System.exit(1);
		}
		//HueBridge.create("10.206.194.13");
		
		hue_bridge = HueBridge.getHueBridge();
		HueStateReader.create(hue_bridge.getBridgeIP());
		hue_state_reader = HueStateReader.getHueReader();
		HueStateWriter.create(hue_bridge.getBridgeIP());
		hue_state_writer = HueStateWriter.getHueWriter();
	}
	
	public static void create(IPStatusEnum function, String argument){
		if(!is_created){
			hue_communicator = new HueCommunicator(function, argument);
		}
		System.out.println("Instance of HueCommunicator created.");
	}
	
	public static HueCommunicator getCommunicator(){
		return hue_communicator;
	}
	
	public static HueBridge getBridge(){
		return hue_bridge;
	}
	
	public String getBridgeIP(){
		return hue_bridge.getBridgeIP();
	}
	
	public boolean hasBeenCreated(){
		return is_created;
	}
	
	public HueStateReader getStateReader(){
		return hue_state_reader;
	}
	public HueStateWriter getStateWriter(){
		return hue_state_writer;
	}
}
