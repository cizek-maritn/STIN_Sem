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
import java.io.OutputStream;
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
import java.sql.*;

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
    public static String handleApiFullCall(@RequestBody Map<String, String> formData) throws URISyntaxException, FileNotFoundException, IOException, SQLException {
        // Process the data (e.g., save to database)
        String lat = formData.get("lat");
        String lon = formData.get("lon");
        
        // Prepare response data
        String responseCode = ApiCaller.CallApiFull(lat, lon);
        Statement s=Application.dbCon.createStatement();
        String sql="UPDATE TEMP set MSG='"+responseCode+"' WHERE ID=1;";
        s.execute(sql);
        s.close();
        return getFullForecast(responseCode);
    }

    @GetMapping("/api/forecast")
    public static String getFullForecast(String s) throws URISyntaxException, IOException, SQLException {
        try (Statement st = Application.dbCon.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM TEMP");
            String msg="";
            while (rs.next()) {
                msg=rs.getString("MSG");
            }
            rs.close();
            st.close();
            return msg;
        }
    }
    
    @PostMapping("/api/hist")
    public static String handleApiFullHistCall(@RequestBody Map<String, String> formData) throws URISyntaxException, FileNotFoundException, IOException, SQLException {
        // Process the data (e.g., save to database)
        String lat = formData.get("lat");
        String lon = formData.get("lon");
        String date = formData.get("dat");
        
        // Prepare response data
        String responseCode = ApiCaller.CallApiFullHist(lat, lon, date);
        Statement s=Application.dbCon.createStatement();
        String sql="UPDATE TEMP set MSG='"+responseCode+"' WHERE ID=1;";
        s.execute(sql);
        s.close();
        return getFullHistory(responseCode);
    }

    @GetMapping("/api/hist")
    public static String getFullHistory(String s) throws URISyntaxException, IOException, SQLException {
        try (Statement st = Application.dbCon.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM TEMP");
            String msg="";
            while (rs.next()) {
                msg=rs.getString("MSG");
            }
            rs.close();
            st.close();
            return msg;
        }
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
    public static RedirectView handleLoginAttempt(@RequestParam("name") String name, @RequestParam("pwd") String pwd, RedirectAttributes ra) throws URISyntaxException, IOException, SQLException {
        String s = Account.login(name, pwd);
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
    public static RedirectView handleRegisterAttempt(@RequestParam("name") String name, @RequestParam("pwd") String pwd, @RequestParam("card") long card, RedirectAttributes ra) throws URISyntaxException, IOException, SQLException {
        int s = Account.register(name, pwd, card);
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
    public static int handleNewPlace(@RequestBody Map<String, String> formData) throws URISyntaxException, FileNotFoundException, SQLException {
        String user = formData.get("user");
        String n = formData.get("placeName");
        String lat = formData.get("lat");
        String lon = formData.get("lon");
        
        try (Statement s = Application.dbCon.createStatement()) {
            String data=n+";"+lat+";"+lon;
            String sql="INSERT INTO "+user+" (PLACES) VALUES ("+data+");";
            s.execute(sql);
            return 1;
        }
    }
    
    @PostMapping("/loadPlaces")
    public static String[] handlePlaceLoad(@RequestBody Map<String, String> formData) throws URISyntaxException, IOException, SQLException {
        String user = formData.get("user");
        //System.out.println("pog");
        
        try (Statement st = Application.dbCon.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM "+user);
            List<String> lines=new ArrayList<>();
            String place;
            while (rs.next()) {
                place=rs.getString("PLACES");
                lines.add(place);
            }
            rs.close();
            st.close();
            String[] output = new String[lines.size()];
            output=lines.toArray(output);
            return output;
        }
    }
}
