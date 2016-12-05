package com.example.snowt.omnipucks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Local {

    @Expose @SerializedName("nom")
    private String nom;

    @Expose @SerializedName("typeLocal")
    private String typeLocal;

    @Expose @SerializedName("numero")
    private String numero;

}
