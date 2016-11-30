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

    @GET("/mobile/GetEventsMobile?id=200153751")



    void listEvents(Callback<List<Event>> eventsCallback);

}