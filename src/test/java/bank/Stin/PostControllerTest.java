/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package bank.Stin;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PostControllerTest {
    
    public PostControllerTest() {
    }
    
    @InjectMocks
    private PostController postController;

    @Test
    public void PostControllerCorrect() {
        Map<String, String> formData = new HashMap<>();
        formData.put("lat","50.23");
        formData.put("lon","50.23");
        formData.put("days","1");
        
        int result = PostController.handleApiCall(formData);
        Assertions.assertNotEquals(-1, result);
    }
    
    @Test
    public void PostControllerWrong() {
        Map<String, String> formData = new HashMap<>();
        formData.put("lat","91.23");
        formData.put("lon","50.23");
        formData.put("days","1");
        
        int result = PostController.handleApiCall(formData);
        Assertions.assertEquals(-1, result);
    }
}
