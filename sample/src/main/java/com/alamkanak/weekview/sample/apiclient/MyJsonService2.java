package com.alamkanak.weekview.sample.apiclient;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by e1349279 on 2016-11-21.
 */

public interface MyJsonService2 {



    void listEvents(Callback<List<Event>> eventsCallback);

}