package com.example.snowt.omnipucks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by snowt on 2016-12-05.
 */

public class Prof implements Serializable {


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Expose @SerializedName("nom")
    private String nom;
    @Expose @SerializedName("id")
    private String id;

    @Override
    public String toString(){
        return nom;

    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Prof){
            Prof c = (Prof)obj;
            if(c.getNom().equals(nom) && c.getId()==id ) return true;
        }

        return false;
    }

}
