/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cizek.martin.backend.struktury;

/**
 *
 * @author Dell
 */
public class Konto {
    private final String mena;
    private float zustatek;
    private float kurzCzk;
    
    public Konto(String m, float z, float k) {
        this.mena=m;
        this.zustatek=z;
        this.kurzCzk=k;
    }

    public String getMena() {
        return mena;
    }

    public float getZustatek() {
        return zustatek;
    }

    public float getKurzCzk() {
        return kurzCzk;
    }

    public void setZustatek(float zustatek) {
        this.zustatek = zustatek;
    }

    public void setKurzCzk(float kurzCzk) {
        this.kurzCzk = kurzCzk;
    }
    
    
}
