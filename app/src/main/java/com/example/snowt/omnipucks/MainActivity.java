package com.example.snowt.omnipucks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void startFrag (View v){
        Intent tempIntent = new Intent(MainActivity.this, TestFragment.class);
        //Intent tempIntent = new Intent(MainActivity.this, FragmentActivity.class);
        startActivity(tempIntent);

    }

    //juste pour un fuckin commit
}
