/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package bank.Stin;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
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
public class AccountTest {
    
    public AccountTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void loginTestCorrect() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("testfiles/login.txt");
        File login = new File (resource.getFile());
        File f = Account.login(login, "houba", "password");
        Assertions.assertNotNull(f);
        Assertions.assertTrue(f instanceof File);
    }
    
    @Test
    public void loginTestWrong() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("testfiles/login.txt");
        File login = new File (resource.getFile());
        File f = Account.login(login, "uagfbailjd", "password");
        Assertions.assertNull(f);
    }
    
    @Test
    public void registerTestCorrect() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("testfiles/login.txt");
        File login = new File (resource.getFile());
        File f = Account.register(login, "alfons", "password", 1234567890123456L);
        Assertions.assertNotNull(f);
        Assertions.assertTrue(f instanceof File);
    }
    
    @Test
    public void registerTestWrongName() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("testfiles/login.txt");
        File login = new File (resource.getFile());
        File f = Account.register(login, "(lk=56./", "password", 1234567890123456L);
        Assertions.assertNull(f);
    }
    
    @Test
    public void registerTestWrongPass() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("testfiles/login.txt");
        File login = new File (resource.getFile());
        File f = Account.register(login, "horac", "--- aihsf+1=", 1234567890123456L);
        Assertions.assertNull(f);
    }
    
    @Test
    public void registerTestWrongCard() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("testfiles/login.txt");
        File login = new File (resource.getFile());
        File f = Account.register(login, "horac", "password", 65124);
        Assertions.assertNull(f);
    }
}
