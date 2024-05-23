/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package bank.Stin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ResourceLoader;
import java.sql.*;

/**
 *
 * @author Dell
 */
public class AccountTest {
    
    public AccountTest() {
    }
    /**
    @Test
    public void loginTestCorrect() throws IOException {
        String path = "data/login.txt";
        
        InputStream is = ResourceLoader.class.getClassLoader().getResourceAsStream(path);
        String s = Account.login(is, "houba", "password");
        Assertions.assertEquals("houba",s);
    }
    
    @Test
    public void loginTestWrong() throws IOException {
        String path = "data/login.txt";
        
        InputStream is = ResourceLoader.class.getClassLoader().getResourceAsStream(path);
        String s = Account.login(is, "uagfbailjd", "password");
        Assertions.assertNull(s);
    }
    
    @Test
    public void registerTestCorrect() throws URISyntaxException, IOException, SQLException {
        String path = "testfiles/login.txt";
        
        OutputStream fos = new FileOutputStream(path,true);
        int i = Account.register("alfons", "Password1", 1234567890123456L);
        Assertions.assertEquals(1,i);
    }
    
    @Test
    public void registerTestWrongName() throws URISyntaxException, IOException {
        String path = "data/login.txt";
        
        OutputStream fos = new FileOutputStream(path,true);
        int i = Account.register(fos, "(lk=56./", "Password1", 1234567890123456L);
        Assertions.assertEquals(2,i);
    }
    
    @Test
    public void registerTestWrongPass() throws URISyntaxException, IOException {
        String path = "testfiles/login.txt";
        
        OutputStream fos = new FileOutputStream(path,true);
        int i = Account.register(fos, "horac", "--- aihsf+1=", 1234567890123456L);
        Assertions.assertEquals(3,i);
    }
    
    @Test
    public void registerTestWrongCard() throws URISyntaxException, IOException {
        String path = "data/login.txt";
        
        OutputStream fos = new FileOutputStream(path,true);
        int i = Account.register(fos, "horac", "Password1", 65124);
        Assertions.assertEquals(4,i);
    }
    **/
}
