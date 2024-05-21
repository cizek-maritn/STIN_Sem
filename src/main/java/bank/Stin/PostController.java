/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank.Stin;

import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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
    
    @PostMapping("/submitLogin")
    public static RedirectView handleLoginAttempt(@RequestParam("name") String name, @RequestParam("pwd") String pwd, RedirectAttributes ra) {
        File f = new File("src/main/resources/data/login.txt");
        String s = Account.login(f, name, pwd);
        if (s!=null) {
            ra.addAttribute("name", name);
            //System.out.println(ra);
            return new RedirectView("success.html");
        } else {
            ra.addAttribute("info", 1);
            return new RedirectView("login.html");
        }
    }
    
    @PostMapping("/submitRegister")
    public static RedirectView handleRegisterAttempt(@RequestParam("name") String name, @RequestParam("pwd") String pwd, @RequestParam("card") long card, RedirectAttributes ra) {
        File f = new File("src/main/resources/data/login.txt");
        int s = Account.register(f, name, pwd, card);
        if (s==1) {
            ra.addAttribute("name", name);
            //System.out.println(ra);
            return new RedirectView("success.html");
        } else {
            ra.addAttribute("info", s);
            return new RedirectView("register.html");
        }
    }
}
