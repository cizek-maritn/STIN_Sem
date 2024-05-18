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
    public static File login(File f, String n, String p) {
        try {
            Scanner reader = new Scanner(f);
            while (reader.hasNextLine()) {
                String[] line=reader.nextLine().split(";");
                if (line[0].equals(n) && line[1].equals(p)) {
                    return new File(n+".txt");
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find login info file.");
        }
        return null;
    }
    
    public static File register(File f, String n, String p, long c) {
        boolean nCheck=DataChecker.stringChecker(n);
        boolean pCheck=DataChecker.stringChecker(p);
        boolean cCheck=DataChecker.cardChecker(c);
        
        if (nCheck && pCheck && cCheck) {
            try {
                FileWriter fw = new FileWriter(f, true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(n+";"+p+";"+c);
                bw.newLine();
                bw.close();
                File newFile = new File("./"+n+".txt");
                newFile.createNewFile();
                return newFile;
            } catch (IOException e) {
                System.out.println("Couldn't find login info file.");
            }
        } else {
            if (!nCheck) {
                System.out.println("Name has to be English alphabet and numbers only.");
            }
            if (!pCheck) {
                System.out.println("Password has to be English alphabet and numbers only.");
            }
            if (!cCheck) {
                System.out.println("Number doesn't belong to a card.");
            }
            return null;
        }        
        return null;
    }
}
