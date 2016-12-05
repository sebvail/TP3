package com.example.snowt.omnipucks;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by e1349279 on 2016-11-21.
 */

public interface MyJsonServiceOff {

    @GET("/mobile/GetEventsMobile/")
    void listEvents(Callback<List<Event>> eventsCallback);

    @GET("/mobile/ListeProfs")
    void listProfs(Callback<List<Prof>> callback);

    @GET("/mobile/Listelocaux")
    void listLocaux(Callback<List<Local>> locauxCallback);

}