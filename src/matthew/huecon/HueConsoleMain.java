package matthew.huecon;

import matthew.huecon.huecomm.HueCommunicator;
import matthew.huecon.huecomm.HueStateReader;
import matthew.huecon.huecomm.HueStateWriter;
import matthew.huecon.huecomm.IPStatusEnum;
import matthew.huecon.util.HueIP;
import matthew.huecon.util.HueTimer;

public class HueConsoleMain {

	public static void main(String[] args) {
		System.out.println("Begining setup: ");
		// IP: 10.206.194.13
		HueCommunicator comm;
		
//		while(!HueIP.isAddressReachable("10.206.194.13", 1000)){
//			System.out.println("Unable to reach host. Trying again in 30 seconds...");
//			HueTimer.waits(5);
//		}
		
		System.out.println("Host Reached: Continuing...");
		
		if(args.length==0){
			HueCommunicator.create(IPStatusEnum.USE_PREVIOUS, null);
		}else if(args.length == 1){
			if(args[0].equals("0")){
				HueCommunicator.create(IPStatusEnum.USE_PREVIOUS, null);
			}else if(args[0].equals("1")){
				HueCommunicator.create(IPStatusEnum.USE_UPNP,null);
			}else if(args[0].equals("2")){
				System.err.println("ERROR: Invalid number of aguments. Format is: jarname 2 <ip>");
				System.exit(0);
			}else{
				System.err.println("ERROR: Invaild function type. Use:\n0 - Use Previous\n1 - Use UPNP service\n2 - Use IP (Type IP after the '2')");
				System.exit(0);
			}
		}else if(args.length == 2){
			HueCommunicator.create(IPStatusEnum.USE_COMMAND, args[1]);
		}
		
		comm = HueCommunicator.getCommunicator();
		
		HueStateReader reader = comm.getStateReader();
		HueStateWriter writer = comm.getStateWriter();
		
		
		System.out.println("Setup complete. Begining Light Functions...");
		try{
			System.out.println("Light 1 is on? "+reader.isLightOn(1));
			writer.setAllOn();
			HueTimer.waitms(10000);
			System.out.println("Light 2 is on? "+reader.isLightOn(1));
			writer.setAllOff();
		}catch(Exception e){
			System.out.println("Don't want to deal with this");
		}
		
		//Initialize Graphic Window
	}

}
