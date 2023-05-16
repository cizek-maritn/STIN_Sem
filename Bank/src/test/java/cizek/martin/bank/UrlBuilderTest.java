/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package cizek.martin.bank;

import cizek.martin.backend.KurzyHtml;
import java.util.Calendar;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Dell
 */
public class UrlBuilderTest {
    
    public UrlBuilderTest() {
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
    public void Url14kveten() {
        Calendar kal=Calendar.getInstance();
        kal.set(2023, 4, 14);
        assertEquals("https://www.cnb.cz/cs/financni-trhy/devizovy-trh/kurzy"
                + "-devizoveho-trhu/kurzy-devizoveho-trhu/denni_kurz.txt;"
                + "jsessionid=20E48284AE75A685CD8C35D4A26A9D08?date=14.05.2023",
                KurzyHtml.UrlBuilder(kal));
    }
    
    @Test
    public void Url29unor() {
        Calendar kal=Calendar.getInstance();
        kal.set(2024, 1, 29);
        assertEquals("https://www.cnb.cz/cs/financni-trhy/devizovy-trh/kurzy"
                + "-devizoveho-trhu/kurzy-devizoveho-trhu/denni_kurz.txt;"
                + "jsessionid=20E48284AE75A685CD8C35D4A26A9D08?date=29.02.2024",
                KurzyHtml.UrlBuilder(kal));
    }
    
    @Test
    public void UrlSilvestr() {
        Calendar kal=Calendar.getInstance();
        kal.set(2023, 11, 31);
        assertEquals("https://www.cnb.cz/cs/financni-trhy/devizovy-trh/kurzy"
                + "-devizoveho-trhu/kurzy-devizoveho-trhu/denni_kurz.txt;"
                + "jsessionid=20E48284AE75A685CD8C35D4A26A9D08?date=31.12.2023",
                KurzyHtml.UrlBuilder(kal));
    }
}
