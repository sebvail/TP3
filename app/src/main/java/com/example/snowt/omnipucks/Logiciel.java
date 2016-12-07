package com.example.snowt.omnipucks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Gabriel on 06/12/2016.
 */

public class Logiciel {

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    @Expose
    @SerializedName("nom")
    private String nom;

    @Override
    public String toString(){
        return nom;

    }
}
