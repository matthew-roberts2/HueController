package matthew.huecon.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class HueIP {
	public static boolean isAddressReachable(String ipAddress, int timeout){
		
		//NetworkInterface
		
		try {
			InetAddress addressToCheck = InetAddress.getByName(ipAddress);
			System.out.println("Address Created: "+addressToCheck.getHostAddress());
			boolean gotIt = addressToCheck.isReachable(timeout);
			System.out.println("Address was Reachable? "+gotIt);
			return gotIt;
		} catch (UnknownHostException e) {
			System.out.println("Problem with getByAddress() : " + e);
		} catch (IOException e){
			System.out.println("Problem with isReachable() : " + e);
		}
		return false;
	}
}
