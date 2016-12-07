package com.example.snowt.omnipucks;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;

public class TriLocal extends Fragment {

    private View rootView;

    private Spinner spinType, spinJour, spinLogiciel;
    private EditText etTimeDebut, etTimeFin;
    private Button btnTri;


    private ArrayList<String> types = new ArrayList<String>(Arrays.asList(""));
    private ArrayList<String> logiciels = new ArrayList<String>(Arrays.asList(""));
    private ArrayList<String> jours = new ArrayList<String>(Arrays.asList("", "Lundi","Mardi", "Mercredi", "Jeudi", "Vendredi"));

    private ArrayList<Local> locaux = new ArrayList<Local>();

    private ArrayAdapter<String> adapterType, adapterJour, adapterLogiciel;
    private ArrayAdapter<Local> adapterLocal;

    private ListView lvTri;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.activity_tri_local, container, false);

        btnTri = (Button) rootView.findViewById(R.id.btnTri);

        spinType = (Spinner) rootView.findViewById(R.id.spinType);
        spinJour = (Spinner) rootView.findViewById(R.id.spinJour);
        spinLogiciel = (Spinner) rootView.findViewById(R.id.spinLogiciel);

        etTimeDebut = (EditText) rootView.findViewById(R.id.etTimeDebut);
        etTimeFin = (EditText) rootView.findViewById(R.id.etTimeFin);

        lvTri = (ListView) rootView.findViewById(R.id.lvTri);



        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://d53equipe5.sv55.cmaisonneuve.qc.ca/")
                .setClient(new OkClient())
                .build();

        MyJsonServiceOff jsonServiceLocal = restAdapter.create(MyJsonServiceOff.class);

        Callback<List<Local>> localHCallback = new Callback<List<Local>>() {
            @Override
            public void success(List<Local> liste, Response response) {
                for (Local local : liste) {

                    if (types.contains(local.getTypelocal()) == false){
                        types.add(local.getTypelocal());
                    }

                    for (String log: local.getLogiciels())
                    {
                        if (logiciels.contains(log) == false){
                            logiciels.add(log);
                        }

                    }
                }

                adapterType = new ArrayAdapter<String>(rootView.getContext(),android.R.layout.simple_spinner_item,types);
                adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinType.setAdapter(adapterType);

                adapterJour = new ArrayAdapter<String>(rootView.getContext(),android.R.layout.simple_spinner_item,jours);
                adapterJour.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinJour.setAdapter(adapterJour);

                adapterLogiciel = new ArrayAdapter<String>(rootView.getContext(),android.R.layout.simple_spinner_item,logiciels);
                adapterLogiciel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinLogiciel.setAdapter(adapterLogiciel);


            }

            @Override
            public void failure(RetrofitError error) {
                String errorString = error.toString();

            }
        };

        jsonServiceLocal.listLocalH(localHCallback);


        btnTri.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Tri();
            }
        });







        return rootView;
    }


    public void Tri(){

        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Cookie",".ASPXAUTH="+ TestFragment.getCookieAuth());
                request.addHeader("Cookie","__RequestVerificationToken"+"="+ TestFragment.getCookieKey());
                request.addQueryParam("local",spinType.getSelectedItem().toString());
                request.addQueryParam("date",Integer.toString(spinJour.getSelectedItemPosition()));
                request.addQueryParam("heureDebut",etTimeDebut.getText().toString());
                request.addQueryParam("heureFin", etTimeFin.getText().toString());
                request.addQueryParam("logiciel",spinLogiciel.getSelectedItem().toString());


            }
        };


        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://d53equipe5.sv55.cmaisonneuve.qc.ca/")
                .setClient(new OkClient())
                .setRequestInterceptor(requestInterceptor)
                .build();

        MyJsonServiceOff jsonServiceLocal = restAdapter.create(MyJsonServiceOff.class);

        Callback<List<Local>> localHCallback = new Callback<List<Local>>() {
            @Override
            public void success(List<Local> liste, Response response) {
                locaux.clear();
                for (Local local : liste) {
                    locaux.add(local);
                }
                LocalAdapter localAdapter = new LocalAdapter(rootView.getContext(), R.layout.itemlistelocal, locaux);
                lvTri.setAdapter(localAdapter);


            }

            @Override
            public void failure(RetrofitError error) {
                String errorString = error.toString();

            }
        };

        jsonServiceLocal.triLocaux(localHCallback);
    }

}