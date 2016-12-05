package com.example.snowt.omnipucks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by snowt on 2016-12-05.
 */

public class Prof implements Serializable {


    @Expose @SerializedName("nom")
    private String nom;
    @Expose @SerializedName("id")
    private String id;

}
