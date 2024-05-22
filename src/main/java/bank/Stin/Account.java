/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank.Stin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


class Account {    

    //returns file with the accounts favorite places
    public static String login(File f, String n, String p) {
        try {
            Scanner reader = new Scanner(f);
            while (reader.hasNextLine()) {
                String[] line=reader.nextLine().split(";");
                if (line[0].equals(n) && line[1].equals(p)) {
                    return line[0];
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find login info file.");
        }
        return null;
    }
    
    public static int register(File f, String n, String p, long c) {
        boolean nCheck=DataChecker.stringChecker(n);
        boolean pCheck=DataChecker.stringChecker(p) && DataChecker.passChecker(p);
        boolean cCheck=DataChecker.cardChecker(c);
        
        if (nCheck && pCheck && cCheck) {
            try {
                FileWriter fw = new FileWriter(f, true);
                System.out.println(f);
                try (BufferedWriter bw = new BufferedWriter(fw)) {
                    bw.write(n+";"+p+";"+c);
                    bw.newLine();
                    bw.close();
                }
                fw.close();
                
                File newFile = new File("src/main/resources/data/"+n+".txt");
                newFile.createNewFile();
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
