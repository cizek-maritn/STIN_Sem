/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank.Stin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.JarURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class PostController {
    
    @PostMapping("/submit")
    public static int[] handleApiCall(@RequestBody Map<String, String> formData) {
        // Process the data (e.g., save to database)
        String lat = formData.get("lat");
        String lon = formData.get("lon");
        String day = formData.get("days");
        
        // Prepare response data
        int[] responseCode = ApiCaller.CallApiCurrent(lat, lon, day);
        return responseCode;
    }
    
    @PostMapping("/api/forecast")
    public static String handleApiFullCall(@RequestBody Map<String, String> formData) throws URISyntaxException, FileNotFoundException, IOException {
        // Process the data (e.g., save to database)
        String lat = formData.get("lat");
        String lon = formData.get("lon");
        
        // Prepare response data
        String responseCode = ApiCaller.CallApiFull(lat, lon);
        String path = "data/temp.txt";
        FileOutputStream fos = new FileOutputStream(path);
        try {
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos));
            pw.close();
            fos.flush();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
        return getFullForecast(responseCode);
    }

    @GetMapping("/api/forecast")
    public static String getFullForecast(String s) throws URISyntaxException, IOException {
        String path = "data/temp.txt";
        InputStream is = ResourceLoader.class.getClassLoader().getResourceAsStream(path);
        
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String output="";
            String line;
            while ((line = br.readLine())!=null ) {
                output+=line;
            }
            br.close();
            is.close();
            return output;
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find login info file.");
        }
        return null;
    }
    
    @PostMapping("/api/hist")
    public static String handleApiFullHistCall(@RequestBody Map<String, String> formData) throws URISyntaxException, FileNotFoundException, IOException {
        // Process the data (e.g., save to database)
        String lat = formData.get("lat");
        String lon = formData.get("lon");
        String date = formData.get("dat");
        
        // Prepare response data
        String responseCode = ApiCaller.CallApiFullHist(lat, lon, date);
        String path = "data/temp.txt";
        FileOutputStream fos = new FileOutputStream(path);
        try {
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos));
            pw.close();
            fos.flush();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
        return getFullHistory(responseCode);
    }

    @GetMapping("/api/hist")
    public static String getFullHistory(String s) throws URISyntaxException, IOException {
        String path = "data/temp.txt";
        InputStream is = ResourceLoader.class.getClassLoader().getResourceAsStream(path);
        
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String output="";
            String line;
            while ((line = br.readLine())!=null ) {
                output+=line;
            }
            br.close();
            is.close();
            return output;
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find login info file.");
        }
        return null;
    }
    
    @PostMapping("/submitHist")
    public static int[] handleApiCallHist(@RequestBody Map<String, String> formData) {
        // Process the data (e.g., save to database)
        String lat = formData.get("lat");
        String lon = formData.get("lon");
        String date = formData.get("dat");
        
        // Prepare response data
        int[] responseCode = ApiCaller.callApiHistoric(lat, lon, date);
        return responseCode;
    }
    
    @PostMapping("/submitLogin")
    public static RedirectView handleLoginAttempt(@RequestParam("name") String name, @RequestParam("pwd") String pwd, RedirectAttributes ra) throws URISyntaxException, IOException {
        String path = "data/login.txt";
        
        InputStream is = ResourceLoader.class.getClassLoader().getResourceAsStream(path);
        System.out.println(is);
        String s = Account.login(is, name, pwd);
        if (s!=null) {
            ra.addAttribute("name", name);
            //System.out.println(ra);
            return new RedirectView("forecast.html");
        } else {
            ra.addAttribute("info", 1);
            return new RedirectView("login.html");
        }
    }
    
    @PostMapping("/submitRegister")
    public static RedirectView handleRegisterAttempt(@RequestParam("name") String name, @RequestParam("pwd") String pwd, @RequestParam("card") long card, RedirectAttributes ra) throws URISyntaxException, IOException {
        String path = "data/login.txt";
        
        FileOutputStream fos = new FileOutputStream(path,true);
        System.out.println(fos);
        int s = Account.register(fos, name, pwd, card);
        if (s==1) {
            ra.addAttribute("name", name);
            //System.out.println(ra);
            return new RedirectView("forecast.html");
        } else {
            ra.addAttribute("info", s);
            return new RedirectView("register.html");
        }
    }
    
    @PostMapping("/submitPlace")
    public static int handleNewPlace(@RequestBody Map<String, String> formData) throws URISyntaxException, FileNotFoundException {
        String user = formData.get("user");
        String n = formData.get("placeName");
        String lat = formData.get("lat");
        String lon = formData.get("lon");
        String path = "data/"+user+".txt";
        FileOutputStream fos = new FileOutputStream(path,true);
        try {
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos));
            pw.println(n+";"+lat+";"+lon);
            pw.close();
            fos.flush();
            return 1;
        } catch (IOException e) {
            System.out.println("Couldn't find favorite info file.");
        }
        return 0;
    }
    
    @PostMapping("/loadPlaces")
    public static String[] handlePlaceLoad(@RequestBody Map<String, String> formData) throws URISyntaxException, IOException {
        String user = formData.get("user");
        //System.out.println("pog");
        String path = "data/"+user+".txt";
        InputStream is = ResourceLoader.class.getClassLoader().getResourceAsStream(path);
        List<String> lines=new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String Line;
            while ((Line = br.readLine())!=null ) {
                lines.add(Line);
            }
            br.close();
            is.close();
            String[] output = new String[lines.size()];
            output=lines.toArray(output);
            return output;
        } catch (FileNotFoundException e) {
            System.out.println("Couldnt find favorite place file");
        }
        return null;
    }
}
