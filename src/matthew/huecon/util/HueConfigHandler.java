package matthew.huecon.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import matthew.huecon.reference.ConfigReference;

public class HueConfigHandler {
	private static HueConfigHandler instance;
	private String configFileLoc;
	private static boolean created = false;
	
	private BufferedReader reader;
	private BufferedWriter writer;
	
	private HueConfigHandler(String configFile){
		this.configFileLoc = configFile;
		created = true;
		reader = getBufferedReaderForFile();
		writer = getBufferedWriterForFile();
	}
	
	/**
	 * Initialize the instance of the class
	 */
	public static void create(){
		if(!created){
			instance = new HueConfigHandler(ConfigReference.configFileLocation);
		}
	}
	
	/**
	 * 
	 * @return the instance of the class
	 */
	public static HueConfigHandler instance(){
		return instance;
	}
	
	private BufferedReader getBufferedReaderForFile(){
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(configFileLoc);
			br = new BufferedReader(fr);
		} catch (FileNotFoundException e) {
			System.out.println("Config file not found. Generating new one...");
			File file = new File(configFileLoc);
			try {
				file.createNewFile();
			} catch (IOException e1) {
				System.err.println("Error creating the file. Maybe the directory is read-only?");
				e1.printStackTrace();
				System.exit(1);
			}
			return getBufferedReaderForFile();
		}
		return br;
	}
	
	private BufferedWriter getBufferedWriterForFile(){
		FileWriter fw = null;
		BufferedWriter bw = null;
		try{
			fw = new FileWriter(configFileLoc);
			bw = new BufferedWriter(fw);
		}catch(Exception e){
			
		}
		return null;
	}
}
