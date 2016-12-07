package com.example.snowt.omnipucks;

import android.graphics.RectF;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.alamkanak.weekview.DateTimeInterpreter;
import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;

public class HoraireEtudiant extends Fragment implements WeekView.EventClickListener, MonthLoader.MonthChangeListener, WeekView.EventLongPressListener, WeekView.EmptyViewLongPressListener, Callback<List<Event>> {

    private static final int TYPE_DAY_VIEW = 1;
    private static final int TYPE_THREE_DAY_VIEW = 2;
    private static final int TYPE_WEEK_VIEW = 3;
    private int mWeekViewType = TYPE_WEEK_VIEW;

    private List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
    private ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();
    boolean calledNetwork = false;
    private View rootView;
    private Spinner spinEtudiant;
    private String etudiantchoisi;

    ArrayAdapter<Etudiant> adapter;

    private WeekView mWeekView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.activity_horaire_etudiant, container, false);

        spinEtudiant = (Spinner) rootView.findViewById(R.id.spinEtudiant);

        // Get a reference for the week view in the layout.
        mWeekView = (WeekView) rootView.findViewById(R.id.weekViewEtudiant);

        // Show a toast message about the touched event.
        mWeekView.setOnEventClickListener(this);

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        mWeekView.setMonthChangeListener(this);

        // Set long press listener for events.
        mWeekView.setEventLongPressListener(this);

        // Set long press listener for empty view
        mWeekView.setEmptyViewLongPressListener(this);

        // Set up a date time interpreter to interpret how the date and time will be formatted in
        // the week view. This is optional.
        setupDateTimeInterpreter(false);

        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Cookie",".ASPXAUTH="+ TestFragment.getCookieAuth());
                request.addHeader("Cookie","__RequestVerificationToken"+"="+ TestFragment.getCookieKey());
                request.addQueryParam("id",TestFragment.getNumeroDossier());
            }
        };

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://d53equipe5.sv55.cmaisonneuve.qc.ca/")
                .setClient(new OkClient())
                .setRequestInterceptor(requestInterceptor)
                .build();

        MyJsonServiceOff jsonServiceProf = restAdapter.create(MyJsonServiceOff.class);

        Callback<List<Etudiant>> callback = new Callback<List<Etudiant>>() {
            @Override
            public void success(List<Etudiant> liste, Response response) {
                etudiants.clear();
                for (Etudiant etudiant : liste) {

                    etudiants.add(etudiant);
                }

                adapter = new ArrayAdapter<Etudiant>(rootView.getContext(),android.R.layout.simple_spinner_item,etudiants);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinEtudiant.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {
                String errorString = error.toString();

            }
        };

        jsonServiceProf.listEtudiant(callback);




        spinEtudiant.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Etudiant etudiant = etudiants.get(position);
                etudiantchoisi = etudiant.getId();
                calledNetwork = false;
                onMonthChange(2016,12);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        mWeekView.goToHour(8);
        return rootView;

    }
    /**
     * Set up a date time interpreter which will show short date values when in week view and long
     * date values otherwise.
     *
     * @param shortDate True if the date values should be short.
     */
    private void setupDateTimeInterpreter(final boolean shortDate) {
        mWeekView.setDateTimeInterpreter(new DateTimeInterpreter() {
            @Override
            public String interpretDate(Calendar date) {
                SimpleDateFormat weekdayNameFormat = new SimpleDateFormat("EEE", Locale.getDefault());
                String weekday = weekdayNameFormat.format(date.getTime());
                SimpleDateFormat format = new SimpleDateFormat(" M/d", Locale.getDefault());

                // All android api level do not have a standard way of getting the first letter of
                // the week day name. Hence we get the first char programmatically.
                // Details: http://stackoverflow.com/questions/16959502/get-one-letter-abbreviation-of-week-day-of-a-date-in-java#answer-16959657
                if (shortDate)
                    weekday = String.valueOf(weekday.charAt(0));
                return weekday.toUpperCase() + format.format(date.getTime());
            }

            @Override
            public String interpretTime(int hour) {
                return hour > 11 ? (hour - 12) + " PM" : (hour == 0 ? "12 AM" : hour + " AM");
            }
        });
    }

    protected String getEventTitle(Calendar time) {
        return String.format("Event of %02d:%02d %s/%d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.MONTH) + 1, time.get(Calendar.DAY_OF_MONTH));
    }


    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {
        //Toast.makeText(this, "Clicked " + event.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {
        //Toast.makeText(this, "Long pressed event: " + event.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEmptyViewLongPress(Calendar time) {
        //Toast.makeText(this, "Empty view long pressed: " + getEventTitle(time), Toast.LENGTH_SHORT).show();
    }


    public WeekView getWeekView() {
        return mWeekView;
    }
    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {

        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Cookie",".ASPXAUTH="+ TestFragment.getCookieAuth());
                request.addHeader("Cookie","__RequestVerificationToken"+"="+ TestFragment.getCookieKey());
                request.addQueryParam("id",etudiantchoisi);
            }
        };

        // Download events from network if it hasn't been done already. To understand how events are
        // downloaded using retrofit, visit http://square.github.io/retrofit
        if (!calledNetwork) {
            RestAdapter retrofit = new RestAdapter.Builder()
                    .setEndpoint("http://d53equipe5.sv55.cmaisonneuve.qc.ca/")
                    //.setEndpoint("http://10.80.108.82:50788/")
                    .setRequestInterceptor(requestInterceptor)
                    .build();
            MyJsonServiceOff service = retrofit.create(MyJsonServiceOff.class);
            service.listEvents(this);

            calledNetwork = true;
        }

        // Return only the events that matches newYear and newMonth.
        List<WeekViewEvent> matchedEvents = new ArrayList<WeekViewEvent>();
        for (WeekViewEvent event : events) {
            if (eventMatches(event, newYear, newMonth)) {
                matchedEvents.add(event);
            }
        }
        return matchedEvents;
    }

    /**
     * Checks if an event falls into a specific year and month.
     * @param event The event to check for.
     * @param year The year.
     * @param month The month.
     * @return True if the event matches the year and month.
     */
    private boolean eventMatches(WeekViewEvent event, int year, int month) {
        return (event.getStartTime().get(Calendar.YEAR) == year && event.getStartTime().get(Calendar.MONTH) == month - 1) || (event.getEndTime().get(Calendar.YEAR) == year && event.getEndTime().get(Calendar.MONTH) == month - 1);
    }

    @Override
    public void success(List<Event> events, Response response) {
        this.events.clear();
        for (Event event : events) {
            this.events.add(event.toWeekViewEvent());
        }
        getWeekView().notifyDatasetChanged();
    }

    @Override
    public void failure(RetrofitError error) {
        error.printStackTrace();

    }
}