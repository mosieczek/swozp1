package com.example.lab1;

import java.io.Serializable;

public class ModelOceny implements Serializable {
    private String nazwa;
    private int ocena;

    public ModelOceny(String nazwa){
        this.nazwa = nazwa;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }
}
