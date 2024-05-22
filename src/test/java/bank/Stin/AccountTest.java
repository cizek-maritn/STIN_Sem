/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package bank.Stin;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Dell
 */
public class AccountTest {
    
    public AccountTest() {
    }

    @Test
    public void loginTestCorrect() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("data/login.txt");
        File login = new File (resource.getFile());
        String s = Account.login(login, "houba", "password");
        Assertions.assertEquals("houba",s);
    }
    
    @Test
    public void loginTestWrong() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("data/login.txt");
        File login = new File (resource.getFile());
        String s = Account.login(login, "uagfbailjd", "password");
        Assertions.assertNull(s);
    }
    
    @Test
    public void registerTestCorrect() throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("data/login.txt");
        File login = new File (resource.getFile());
        int i = Account.register(login, "alfons", "Password1", 1234567890123456L);
        Assertions.assertEquals(1,i);
    }
    
    @Test
    public void registerTestWrongName() throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("data/login.txt");
        File login = new File (resource.getFile());
        int i = Account.register(login, "(lk=56./", "Password1", 1234567890123456L);
        Assertions.assertEquals(2,i);
    }
    
    @Test
    public void registerTestWrongPass() throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("data/login.txt");
        File login = new File (resource.getFile());
        int i = Account.register(login, "horac", "--- aihsf+1=", 1234567890123456L);
        Assertions.assertEquals(3,i);
    }
    
    @Test
    public void registerTestWrongCard() throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("data/login.txt");
        File login = new File (resource.getFile());
        int i = Account.register(login, "horac", "Password1", 65124);
        Assertions.assertEquals(4,i);
    }
}
