/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank.Stin;

import java.io.BufferedWriter;
import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
    public static String handleApiFullCall(@RequestBody Map<String, String> formData) throws URISyntaxException {
        // Process the data (e.g., save to database)
        String lat = formData.get("lat");
        String lon = formData.get("lon");
        
        // Prepare response data
        String responseCode = ApiCaller.CallApiFull(lat, lon);
        String path = "data/temp.txt";
        URL fileUrl = ResourceLoader.class.getClassLoader().getResource(path);
        File f = new File(fileUrl.toURI());
        try {
            FileWriter myWriter = new FileWriter(f);
            myWriter.write(responseCode);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
        return getFullForecast(responseCode);
    }

    @GetMapping("/api/forecast")
    public static String getFullForecast(String s) throws URISyntaxException {
        String path = "data/temp.txt";
        URL fileUrl = ResourceLoader.class.getClassLoader().getResource(path);
        File f = new File(fileUrl.toURI());
        try {
            Scanner reader = new Scanner(f);
            String output="";
            while (reader.hasNextLine()) {
                output+=reader.nextLine();
            }
            reader.close();
            return output;
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find login info file.");
        }
        return null;
    }
    
    @PostMapping("/api/hist")
    public static String handleApiFullHistCall(@RequestBody Map<String, String> formData) throws URISyntaxException {
        // Process the data (e.g., save to database)
        String lat = formData.get("lat");
        String lon = formData.get("lon");
        String date = formData.get("dat");
        
        // Prepare response data
        String responseCode = ApiCaller.CallApiFullHist(lat, lon, date);
        String path = "data/temp.txt";
        URL fileUrl = ResourceLoader.class.getClassLoader().getResource(path);
        File f = new File(fileUrl.toURI());
        try {
            FileWriter myWriter = new FileWriter(f);
            myWriter.write(responseCode);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
        return getFullHistory(responseCode);
    }

    @GetMapping("/api/hist")
    public static String getFullHistory(String s) throws URISyntaxException {
        String path = "data/temp.txt";
        URL fileUrl = ResourceLoader.class.getClassLoader().getResource(path);
        File f = new File(fileUrl.toURI());
        try {
            Scanner reader = new Scanner(f);
            String output="";
            while (reader.hasNextLine()) {
                output+=reader.nextLine();
            }
            reader.close();
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
    public static RedirectView handleLoginAttempt(@RequestParam("name") String name, @RequestParam("pwd") String pwd, RedirectAttributes ra) throws URISyntaxException {
        String path = "data/login.txt";
        URL fileUrl = ResourceLoader.class.getClassLoader().getResource(path);
        System.out.println("URL: "+fileUrl);
        System.out.println("URI: "+fileUrl.toURI());
        File f = new File(fileUrl.toURI());
        String s = Account.login(f, name, pwd);
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
    public static RedirectView handleRegisterAttempt(@RequestParam("name") String name, @RequestParam("pwd") String pwd, @RequestParam("card") long card, RedirectAttributes ra) throws URISyntaxException {
        String path = "data/login.txt";
        URL fileUrl = ResourceLoader.class.getClassLoader().getResource(path);
        File f = new File(fileUrl.toURI());
        int s = Account.register(f, name, pwd, card);
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
    public static int handleNewPlace(@RequestBody Map<String, String> formData) throws URISyntaxException {
        String user = formData.get("user");
        String n = formData.get("placeName");
        String lat = formData.get("lat");
        String lon = formData.get("lon");
        String path = "data/"+user+".txt";
        URL fileUrl = ResourceLoader.class.getClassLoader().getResource(path);
        File f = new File(fileUrl.toURI());
        try {
            FileWriter fw = new FileWriter(f, true);
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(n+";"+lat+";"+lon);
                    bw.newLine();
                    bw.close();
            }
            fw.close();
            return 1;
        } catch (IOException e) {
            System.out.println("Couldn't find favorite info file.");
        }
        return 0;
    }
    
    @PostMapping("/loadPlaces")
    public static String[] handlePlaceLoad(@RequestBody Map<String, String> formData) throws URISyntaxException {
        String user = formData.get("user");
        //System.out.println("pog");
        String path = "data/"+user+".txt";
        URL fileUrl = ResourceLoader.class.getClassLoader().getResource(path);
        File f = new File(fileUrl.toURI());
        List<String> lines=new ArrayList<>();
        try {
            Scanner reader = new Scanner(f);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                lines.add(line);
            }
            reader.close();
            String[] output = new String[lines.size()];
            output=lines.toArray(output);
            return output;
        } catch (FileNotFoundException e) {
            System.out.println("Couldnt find favorite place file");
        }
        return null;
    }
}
