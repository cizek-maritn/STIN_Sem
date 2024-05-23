/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank.Stin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;
import org.springframework.core.io.ResourceLoader;
import java.sql.*;


class Account {    

    //returns file with the accounts favorite places
    public static String login(String n, String p) throws IOException, SQLException {
        Statement s=Application.dbCon.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM LOGIN;");
        while (rs.next()) {
            String line=rs.getString("INFO");
            String[] data=line.split(";");
                if (data[0].equals(n) && data[1].equals(p)) {
                    return data[0];
                }
        }
        return null;
    }
    
    public static int register(String n, String p, long c) throws URISyntaxException, IOException, SQLException {
        boolean nCheck=DataChecker.stringChecker(n);
        boolean pCheck=DataChecker.stringChecker(p) && DataChecker.passChecker(p);
        boolean cCheck=DataChecker.cardChecker(c);
        
        if (nCheck && pCheck && cCheck) {
            try (Statement s = Application.dbCon.createStatement()) {
                String data=n+";"+p+";"+c;
                String sql="INSERT INTO LOGIN (INFO) VALUES ("+data+");";
                s.execute(sql);
                sql = "CREATE TABLE "+n+" (PLACES TEXT PRIMARY KEY NOT NULL)";
                s.execute(sql);
                s.close();
                return 1;
            }
        } else {
            if (!nCheck) {
                System.out.println("Name has to be English alphabet and numbers only.");
                return 2;
            }
            if (!pCheck) {
                System.out.println("Password has to be English alphabet and numbers only.");
                return 3;
            }
            if (!cCheck) {
                System.out.println("Number doesn't belong to a card.");
                return 4;
            }
        }        
        return 0;
    }
}
