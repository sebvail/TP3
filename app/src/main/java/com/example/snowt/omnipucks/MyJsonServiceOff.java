package com.example.snowt.omnipucks;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;

import com.alamkanak.weekview.*;

/**
 * Created by e1349279 on 2016-11-21.
 */

public interface MyJsonServiceOff {

    @GET("/mobile/GetEventsMobile?id=201458246")
    void listEventsPerso(Callback<List<Event>> eventsCallback);

    @GET("/mobile/GetEventsMobile?id=10264")
    void listEventsProf(Callback<List<Event>> eventsCallback);

    @GET("/mobile/GetEventsMobile?id=201349279")
    void listEventsAutreEtudiant(Callback<List<Event>> eventsCallback);

}