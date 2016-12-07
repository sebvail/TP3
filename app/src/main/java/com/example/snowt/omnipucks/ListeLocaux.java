package com.example.snowt.omnipucks;

import android.app.ListActivity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;

public class ListeLocaux extends Fragment implements Callback<List<Local>> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private View rootView;
    ListView listv;

    ArrayAdapter<Local> adapter;

    private List<Local> locaux = new ArrayList<Local>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListeLocaux() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListeLocaux.
     */
    // TODO: Rename and change types and number of parameters
    public static ListeLocaux newInstance(String param1, String param2) {
        ListeLocaux fragment = new ListeLocaux();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_liste_locaux, container, false);

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

        MyJsonServiceOff jsonServiceLocal = restAdapter.create(MyJsonServiceOff.class);

        Callback<List<Local>> callback = new Callback<List<Local>>() {
            @Override
            public void success(List<Local> locals, Response response) {
                locaux.clear();
                for(Local local: locaux)
                {
                    locaux.add(local);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                String errorString = error.toString();
            }

        };

        jsonServiceLocal.listLocaux(callback);

        adapter = new ArrayAdapter<Local>(rootView.getContext(), android.R.layout.simple_list_item_2, android.R.id.text1, locaux)
        {
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View maView = super.getView(position, convertView, parent);

                TextView text1 = (TextView) maView.findViewById(android.R.id.text1);
                TextView text2 = (TextView) maView.findViewById(android.R.id.text2);

                text1.setText(locaux.get(position).getNom());
                text2.setText(locaux.get(position).getNumero());

                return maView;
            }
        };

        listv = (ListView) rootView.findViewById(R.id.list);
        listv.setListAdapter(adapter);

        return rootView;
    }


    @Override
    public void success(List<Local> locals, Response response) {
        this.locaux.clear();
        for(Local local: locaux)
        {
            this.locaux.add(local);
        }
    }

    @Override
    public void failure(RetrofitError error) {
        error.printStackTrace();
    }

}
