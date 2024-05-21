/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank.Stin;


public class DataChecker {
    //return true for strings containing only the english alphabet and numbers
    public static boolean stringChecker(String s) {
        return s.matches("[a-zA-Z0-9]+");
    }
    
    //assuming the card has 16 digits
    public static boolean cardChecker(long n) {
        String s=Long.toString(n);
        return s.length()==16;
    }
    
    public static boolean passChecker(String s) {
        return s.matches(".*\\d.*") && s.matches(".*[A-Z].*") && s.matches(".*[a-z].*") && s.length()>=6;
    }
}
