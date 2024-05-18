/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package bank.Stin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Dell
 */
public class DataCheckerTest {

    @Test
    public void stringTestCorrect() {
        Assertions.assertTrue(DataChecker.stringChecker("Correct"));
    }
    
    @Test
    public void stringTestNumberCorrect() {
        Assertions.assertTrue(DataChecker.stringChecker("12sdh3"));
    }
    
    @Test
    public void stringTestWrong() {
        Assertions.assertFalse(DataChecker.stringChecker("Jean-Pierre"));
    }
    
    @Test
    public void numTestCorrect() {
        Assertions.assertTrue(DataChecker.cardChecker(1234567890123456L));
    }
    
    @Test
    public void numTestWrong() {
        Assertions.assertFalse(DataChecker.cardChecker(5612));
    }
}
