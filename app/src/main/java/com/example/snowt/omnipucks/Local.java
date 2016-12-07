package com.example.snowt.omnipucks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.ListIterator;

public class Local implements Serializable {

    @Expose @SerializedName("nom")
    private String nom;

    @Expose @SerializedName("numero")
    private String numero;
    @Expose @SerializedName("typeLocal")
    private String typelocal;

    @Expose @SerializedName("logiciels")
    private List<String> logiciels;


    public String getNom() {
        return nom;
    }

    public String getNumero() {
        return numero;
    }

    public List<String> getLogiciels() {
        return logiciels;
    }

    public String getTypelocal() {
        return typelocal;
    }

    @Override
    public String toString(){
        return numero;

    }


}
