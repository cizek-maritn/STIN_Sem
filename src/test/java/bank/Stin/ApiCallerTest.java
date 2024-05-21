/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package bank.Stin;

import java.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Dell
 */
public class ApiCallerTest {
    
    public ApiCallerTest() {
    }
    
    @Test
    public void CurrentApiCallCorrect() {
        int[] arr1 = new int[] {-1};
        int[] arr2 = ApiCaller.CallApiCurrent("50.46","15.3","1");
        Assertions.assertFalse(Arrays.equals(arr1,arr2));
        
    }

    @Test
    public void CurrentApiCallWrong() {
        int[] arr1 = new int[] {-1};
        int[] arr2 = ApiCaller.CallApiCurrent("91.2","15.3","1");
        Assertions.assertArrayEquals(arr1,arr2);
    }
}