package com.example.snowt.omnipucks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Local implements Serializable {

    @Expose @SerializedName("nom")
    private String nom;

    @Expose @SerializedName("typeLocal")
    private String typeLocal;

    @Expose @SerializedName("numero")
    private String numero;

    private String[] listeLocal = {"Liste", "Pour", "Tester"};


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTypeLocal() {
        return typeLocal;
    }

    public void setTypeLocal(String typeLocal) {
        this.typeLocal = typeLocal;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String[] getListeLocal() {
        return listeLocal;
    }

    public void setListeLocal(String[] listeLocal) {
        this.listeLocal = listeLocal;
    }
}
