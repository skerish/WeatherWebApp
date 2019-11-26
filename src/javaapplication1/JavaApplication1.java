
package javaapplication1;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.*;
import com.google.gson.reflect.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaApplication1 {

    public static Map<String, Object> jsonToMap(String str){
        Map<String, Object> map = new Gson().fromJson(
                str, new TypeToken<HashMap<String, Object>>() {}.getType());
                return map;
    }
    
    public static void main(String[] args) {
        String API_KEY = "YOUR_API_KEY";
        String LOCATION = "Mumbai,in";
        String URL_String = "https://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + "&appid=" + API_KEY;
        
        try{
            StringBuilder result = new StringBuilder();
            URL url = new URL(URL_String);
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while((line = reader.readLine()) != null){
                result.append(line);
            }
            reader.close();
            System.out.println(result);
            
            Map<String,Object> respMap = jsonToMap(result.toString());
            Map<String,Object> mainMap = jsonToMap(respMap.get("main").toString());
            Map<String,Object> windMap = jsonToMap(respMap.get("wind").toString());
            
            System.out.println("Current Temperature: " + mainMap.get("temp"));
            System.out.println("Current Humidity: " + mainMap.get("humidity"));
            System.out.println("Wind Speed: " + windMap.get("speed"));
            System.out.println("Wind Angle: " + windMap.get("deg"));
                    
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
