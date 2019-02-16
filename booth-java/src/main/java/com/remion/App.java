package com.remion;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.logging.Level;

public class App {
	private static Logger logger = LoggerFactory.getLogger(App.class);

	private final static String BOOTH_HOST = "10.0.4.246";
	private final static int BOOTH_PORT = 5555;

	private final static String PORTAL_HOST = "demo.remion.com";
	private final static int PORTAL_PORT = 443;

	public static void main(String[] args) {
        BoothConnection con = BoothConnection.create(BOOTH_HOST, BOOTH_PORT);
        con.open();
        RegattaPortal portal = new RegattaPortal(PORTAL_HOST, PORTAL_PORT, "ca040ea5-0255-41d1-80cb-9889d4dc5f0f");
        BufferedReader reader = con.getLineReader();
        
        while(true) {
            String l = "";
            try {
                logger.info("reading a line");
                l = reader.readLine();
            }
            catch (IOException ex) {
          
            }
            logger.info("parsing a line");
            HashMap<String, Object> data = parser(l);
            if(data != null) {
                portal.send(Instant.now(), data);
            }
            
        }
	}
    
    private static HashMap<String, Object> parser(String line) {
        String[] data = line.split(",");
        logger.info(data[0]);
        /*
        if(data[0].equals("#R1")) {
            HashMap<String, Object> parsed = new HashMap<>();
            parsed.put("Presence_in", data[1]);
            parsed.put("Presence_out", (data[2]));
            
            parsed.put("Position_X", data[3]);
            parsed.put("Poition_Y", data[4]);
            
            parsed.put("Standing", (data[5]));
            parsed.put("Height_standing", data[6]);
            
            parsed.put("Sitting", (data[7]));
            parsed.put("Height_sitting", data[8]);
            //9 reserved
            parsed.put("Touching_table", (data[10]));
            parsed.put("object_table", (data[11]));
            
            parsed.put("Touching_handle", (data[12]));
            parsed.put("Distance_handle", data[13]);
            
            parsed.put("Gesturing", (data[14]));
            //15 reserved
            
            parsed.put("feedback", data[16]);
            
            parsed.put("Led_hue", data[17]);
            //18 reserved
            parsed.put("Door_angle", data[19]);
            //20 reserved
            return parsed;
        }
        */
        if(data[0].equals("#S1")) {
            logger.info("inside #S1");
            HashMap<String, Object> parsed = new HashMap<>();
            parsed.put("Temperature", Double.parseDouble(data[1]));
            
            parsed.put("Relative_humidity", Double.parseDouble(data[2]));
            /*
            parsed.put("Air_preddure", Double.parseDouble(data[3]));
            parsed.put("CO2", Double.parseDouble(data[4]));
            
            parsed.put("TVOC", Double.parseDouble(data[5]));
            parsed.put("Visible_spectrum", Double.parseDouble(data[6]));
            
            parsed.put("IR_spectrum", Double.parseDouble(data[7]));
            parsed.put("Illuminance", Double.parseDouble(data[8]));
            parsed.put("Feedback_score", Double.parseDouble(data[9]));
            */
            return parsed;
        }
        else {
          return null;  
        }
        
    }
    private static boolean parseBool(String s) {
        if(s == "1") {
            return true;
        }
        else return false;
    }
}
