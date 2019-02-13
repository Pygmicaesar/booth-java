package com.remion;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.HashMap;

public class App {
	private static Logger logger = LoggerFactory.getLogger(App.class);

	private final static String BOOTH_HOST = "10.0.4.246";
	private final static int BOOTH_PORT = 5555;

	private final static String PORTAL_HOST = "demo.remion.com";
	private final static int PORTAL_PORT = 443;

	public static void main(String[] args) {
        BoothConnection con = BoothConnection.create(BOOTH_HOST, BOOTH_PORT);
        con.open();
        
        BufferedReader reader = con.getLineReader();
        
	}
    
    private HashMap<String, Object> parser(String line) {
        String[] data = line.split(",");
        if(data[0] == "#R1") {
            if(data.length != 20) {return null}
            HashMap<String, Object> parsed = new HashMap()<>;
            parsed.put("Presense inside", Boolean.padata[1]);
            parsed.put("Presence outside", data[2]);
            
            parsed.put("Position X", data[3]);
            parsed.put("Poition Y", data[4]);
            
            parsed.put("Standing", data[5]);
            parsed.put("Height standing", data[6]);
            
            parsed.put("Sitting", data[7]);
            parsed.put("Height sitting", data[8]);
            //9 reserved
            parsed.put("Touching table", data[10]);
            parsed.put("object table", data[11]);
            
            parsed.put("Touching handle", data[12]);
            parsed.put("Distance handle", data[13]);
            
            parsed.put("Gesturing", data[14]);
            //15 reserved
            
            parsed.put("feedback", data[16]);
            
            parsed.put("Led hue", data[17]);
            //18 reserved
            parsed.put("Door angle", data[19]);
            //20 reserved
            
        }
        if(data[0] == "#C1") {
            return null;
        }
        if(data[0] == "#S1") {
            return null;
        }
    }
}
