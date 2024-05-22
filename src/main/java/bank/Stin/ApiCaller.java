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
    public static int[] CallApiCurrent(String lat, String lon, String days) {
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
                    int[] wcodes = new int[Integer.parseInt(days)];
                    for (int i=0;i<Integer.parseInt(days);i++) {
                        wcodes[i]=wcArr.getInt(ct+(i*24));
                    }
                    return wcodes;
                } else {
                    System.err.println("Curl command failed with exit code " + exitCode);
                    return new int[] {-1};
                }
                
            } catch (JSONException e) {
                System.out.println("Missing hourly field.");
                System.out.println(e);
                return new int[] {-1};
            }
            

        } catch (IOException | InterruptedException e) {
            System.out.println("Something completely went wrong: "+e);
        }
        return new int[] {-1};
    }
    
    public static int[] callApiHistoric(String lat, String lon, String date) {
        String apiCall=CallBuilderHistoric(lat,lon,date);
        String[] command = {
            "curl",
            "-X", "GET",
            apiCall, "--ssl-no-revoke"
        };
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
            System.out.println(obj);
            
            try {
                JSONObject hourly = obj.getJSONObject("hourly");
                JSONArray wcArr = hourly.getJSONArray("weather_code");
                //System.out.println(wcArr);
                
                int exitCode = process.waitFor();
                if (exitCode == 0) {
                    int[] wcodes = new int[5];
                    wcodes[4]=wcArr.getInt(23);
                    for (int i=0;i<4;i++) {
                        wcodes[i]=wcArr.getInt(((i*6)));
                    }
                    return wcodes;
                } else {
                    System.err.println("Curl command failed with exit code " + exitCode);
                    return new int[] {-1};
                }
                
            } catch (JSONException e) {
                System.out.println("Missing hourly field.");
                System.out.println(e);
                return new int[] {-1};
            }
            

        } catch (IOException | InterruptedException e) {
            System.out.println("Something completely went wrong: "+e);
        }
        return new int[] {-1};
    }
    
    public static String CallApiFull(String lat, String lon) {
        String apiCall=CallBuilder(lat,lon,"7");
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
            
            return output.toString();

        } catch (IOException e) {
            System.out.println("Something completely went wrong: "+e);
        }
        return null;
    } 
    
    public static String CallApiFullHist(String lat, String lon, String date) {
        String apiCall=CallBuilderHistoric(lat,lon,date);
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
            
            return output.toString();

        } catch (IOException e) {
            System.out.println("Something completely went wrong: "+e);
        }
        return null;
    } 
    
    private static String CallBuilder(String lat, String lon, String days) {
        return "https://api.open-meteo.com/v1/forecast?latitude="+lat+"&longitude="+lon+"&hourly=weather_code&forecast_days="+days;
    }
    
    private static String CallBuilderHistoric(String lat, String lon, String date) {
        return "https://archive-api.open-meteo.com/v1/archive?latitude="+lat+"&longitude="+lon+"&start_date="+date+"&end_date="+date+"&hourly=weather_code";
    }
}