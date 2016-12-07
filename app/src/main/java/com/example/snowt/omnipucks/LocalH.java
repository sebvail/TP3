package com.example.snowt.omnipucks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by snowt on 2016-12-06.
 */

public class LocalH implements Serializable {

    @Expose @SerializedName("nom")
    private String nom;

    @Expose @SerializedName("numero")
    private String numero;

    @Expose @SerializedName("logiciels")
    private List<String> logiciels;



    @Override
    public String toString(){
        return numero;

    }


}
