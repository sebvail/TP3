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

    @GET("/mobile/ListeEtudiantsPublic")
    void listEtudiant(Callback<List<Etudiant>> etudiantsCallback);

    @GET("/mobile/Listelocaux")
    void listLocalH(Callback<List<Local>> localHCallback);

    @GET("/mobile/GetEventsLocal")
    void listEventsLocal(Callback<List<Event>> eventsLocalCallback);

    @GET("/mobile/TriLocaux")
    void triLocaux(Callback<List<Local>> localHCallback);

}