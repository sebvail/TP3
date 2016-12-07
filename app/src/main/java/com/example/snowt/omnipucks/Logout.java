package com.example.snowt.omnipucks;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Logout extends Fragment {

    private View rootView;

    Button btnLogout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.activity_logout, container, false);

        btnLogout = (Button) rootView.findViewById(R.id.btnLogout);


        btnLogout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(rootView.getContext(),MainActivity.class);
                startActivity(intent);
            }
        });



        return rootView;
    }



}
