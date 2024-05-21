/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank.Stin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import org.json.*;

public class ApiCaller {
    public static int CallApiCurrent(String lat, String lon, String days) {
        String apiCall=CallBuilder(lat,lon,days);
        String[] command = {
            "curl",
            "-X", "GET",
            apiCall, "--ssl-no-revoke"
        };
        //System.out.println(command[3]);
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        
        try {
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            
            JSONObject obj = new JSONObject(output.toString());
            
            try {
                JSONObject hourly = obj.getJSONObject("hourly");
                JSONArray wcArr = hourly.getJSONArray("weather_code");
                //System.out.println(wcArr);
                
                int exitCode = process.waitFor();
                if (exitCode == 0) {
                    int ct = LocalTime.now().getHour();
                    return wcArr.getInt(ct);
                } else {
                    System.err.println("Curl command failed with exit code " + exitCode);
                    return -1;
                }
                
            } catch (JSONException e) {
                System.out.println("Missing hourly field.");
                return -1;
            }
            

        } catch (IOException | InterruptedException e) {
            System.out.println("Something completely went wrong: "+e);
        }
        return -1;
    }
    
    private static String CallBuilder(String lat, String lon, String days) {
        return "https://api.open-meteo.com/v1/forecast?latitude="+lat+"&longitude="+lon+"&hourly=weather_code&forecast_days="+days;
    }
}