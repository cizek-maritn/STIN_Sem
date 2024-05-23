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


class Account {    

    //returns file with the accounts favorite places
    public static String login(InputStream is, String n, String p) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine())!=null ) {
                String[] data=line.split(";");
                if (data[0].equals(n) && data[1].equals(p)) {
                    return data[0];
                }
            }
            br.close();
            is.close();
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find login info file.");
        }
        return null;
    }
    
    public static int register(FileOutputStream stream, String n, String p, long c) throws URISyntaxException, IOException {
        boolean nCheck=DataChecker.stringChecker(n);
        boolean pCheck=DataChecker.stringChecker(p) && DataChecker.passChecker(p);
        boolean cCheck=DataChecker.cardChecker(c);
        
        if (nCheck && pCheck && cCheck) {
            try {
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(stream));
                pw.println(n+";"+p+";"+c);
                pw.close();
                stream.flush();
                
                String path = "data/"+n+".txt";
                try (FileOutputStream fos = new FileOutputStream(path)) { 
                }
                return 1;
            } catch (IOException e) {
                System.out.println("Couldn't find login info file.");
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
