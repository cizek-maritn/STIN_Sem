/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package bank.Stin;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {
    
    public PostControllerTest() {
    }
    
    @Autowired
    private MockMvc mvc;

    @Test
    public void PostControllerCorrect() {
        Map<String, String> formData = new HashMap<>();
        formData.put("lat","50.23");
        formData.put("lon","50.23");
        formData.put("days","1");
        
        int[] arr1 = new int[] {-1};
        int[] result = PostController.handleApiCall(formData);
        Assertions.assertFalse(Arrays.equals(arr1,result));
    }
    
    @Test
    public void PostControllerWrong() {
        Map<String, String> formData = new HashMap<>();
        formData.put("lat","91.23");
        formData.put("lon","50.23");
        formData.put("days","1");
        
        int[] arr1 = new int[] {-1};
        int[] result = PostController.handleApiCall(formData);
        Assertions.assertArrayEquals(arr1,result);
    }
    
    @Test
    public void ApiReqPostCorrect() throws URISyntaxException, IOException {
        Map<String, String> formData = new HashMap<>();
        formData.put("lat","50.23");
        formData.put("lon","50.23");
        
        String result = PostController.handleApiFullCall(formData);
        Assertions.assertNotNull(result);
    }
    
    @Test
    public void ApiReqGetCorrect() throws URISyntaxException, IOException {
        
        String result = PostController.getFullForecast("test");
        Assertions.assertNotNull(result);
    }
    
    @Test
    public void ApiReqHistPostCorrect() throws URISyntaxException, IOException {
        Map<String, String> formData = new HashMap<>();
        formData.put("lat","50.23");
        formData.put("lon","50.23");
        formData.put("dat", "2024-05-15");
        
        String result = PostController.handleApiFullHistCall(formData);
        Assertions.assertNotNull(result);
    }
    
    @Test
    public void ApiReqHistGetCorrect() throws URISyntaxException, IOException {
        
        String result = PostController.getFullHistory("test");
        Assertions.assertNotNull(result);
    }
    
    @Test
    public void PostControllerHistCorrect() {
        Map<String, String> formData = new HashMap<>();
        formData.put("lat","50.23");
        formData.put("lon","50.23");
        formData.put("dat","2024-05-15");
        
        int[] arr1 = new int[] {-1};
        int[] result = PostController.handleApiCallHist(formData);
        Assertions.assertFalse(Arrays.equals(arr1,result));
    }
    
    @Test
    public void PostControllerHistWrong() {
        Map<String, String> formData = new HashMap<>();
        formData.put("lat","91.23");
        formData.put("lon","50.23");
        formData.put("dat","2024-05-15");
        
        int[] arr1 = new int[] {-1};
        int[] result = PostController.handleApiCallHist(formData);
        Assertions.assertArrayEquals(arr1,result);
    }
    
    @Test
    public void LoginHandleCorrect() throws Exception {
        mvc.perform(post("/submitLogin")
                .param("name", "test")
                .param("pwd", "test"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("forecast.html?name=test"));
    }
    
    @Test
    public void LoginHandleWorng() throws Exception {
        mvc.perform(post("/submitLogin")
                .param("name", "tes")
                .param("pwd", "test"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("login.html?info=1"));
    }
    
    @Test
    public void RegisterHandleCorrect() throws Exception {
        mvc.perform(post("/submitRegister")
                .param("name", "alfons")
                .param("pwd", "Abcdef12G")
                .param("card", "1234567890123456"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("forecast.html?name=alfons"));
    }
    
    @Test
    public void RegisterHandleWrong() throws Exception {
        mvc.perform(post("/submitRegister")
                .param("name", "alfons")
                .param("pwd", "Abc")
                .param("card", "1234567890123456"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("register.html?info=3"));
    }
    
    @Test
    public void submitPlaceCorrect() throws URISyntaxException, FileNotFoundException {
        Map<String, String> formData = new HashMap<>();
        formData.put("user", "test");
        formData.put("lat","50.23");
        formData.put("lon","50.23");
        formData.put("placeName","somewhere");
        

        int result = PostController.handleNewPlace(formData);
        Assertions.assertEquals(1,result);
    }
    
    @Test
    public void loadPlaceCorrect() throws URISyntaxException, IOException {
        Map<String, String> formData = new HashMap<>();
        formData.put("user", "test");        

        String[] result = PostController.handlePlaceLoad(formData);
        Assertions.assertNotNull(result);
    }
    
    
}
