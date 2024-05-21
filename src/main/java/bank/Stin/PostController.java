/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank.Stin;

import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {
    
    @PostMapping("/submit")
    public static int handleApiCall(@RequestBody Map<String, String> formData) {
        // Process the data (e.g., save to database)
        String lat = formData.get("lat");
        String lon = formData.get("lon");
        String day = formData.get("days");
        
        // Prepare response data
        int responseCode = ApiCaller.CallApiCurrent(lat, lon, day);
        return responseCode;
    }
}