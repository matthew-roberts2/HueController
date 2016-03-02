package matthew.huecon.huecomm;

public class HueBridge {
	private static String bridge_ip = null;
	private static HueBridge hue_bridge;
	private HueBridge(String ip){
		bridge_ip = ip;
	}
	public static void create(String ip){
		hue_bridge = new HueBridge(ip);
	}
	public static HueBridge getHueBridge(){
		return hue_bridge;
	}
	public String getBridgeIP(){
		return bridge_ip;
	}
}
