package matthew.huecon.util;

public class HueTimer {
	/*
	 * Time in seconds to wait
	 */
	public static void waits(int time){
		long initTime = System.currentTimeMillis();
		System.out.println("Waiting " + time +" seconds...");
		long currTime = initTime;
		while(currTime-initTime < time*1000){
			currTime = System.currentTimeMillis();
		}
	}
	public static void waitms(int time){
		long initTime = System.currentTimeMillis();
		System.out.println("Waiting " + time +" milliseconds...");
		long currTime = initTime;
		while(currTime-initTime < time){
			currTime = System.currentTimeMillis();
		}
	}
}
