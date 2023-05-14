/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cizek.martin.backend.struktury;

/**
 *
 * @author Dell
 */
public class Ucet {
    private final String jmeno;
    private final String prijmeni;
    private final String email;
    private final String heslo;
    private final int id;
    
    public Ucet(String j, String p, String e, String h, int i) {
        this.jmeno=j;
        this.prijmeni=p;
        this.email=e;
        this.heslo=h;
        this.id=i;
    }

    public String getJmeno() {
        return jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public String getEmail() {
        return email;
    }

    public String getHeslo() {
        return heslo;
    }

    public int getId() {
        return id;
    }
    
    
}
