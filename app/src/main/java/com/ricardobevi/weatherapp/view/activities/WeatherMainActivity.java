package com.ricardobevi.weatherapp.view.activities;

import android.app.FragmentManager;
import android.os.Bundle;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.ricardobevi.weatherapp.R;
import com.ricardobevi.weatherapp.view.fragments.WeatherListFragment;

public class WeatherMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_main);
    }

    @Override
    protected void onResume() {
        super.onStart();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        WeatherListFragment weatherListFragment = new WeatherListFragment();

        fragmentTransaction.replace(R.id.main_container, weatherListFragment);


        fragmentTransaction.addToBackStack(null);

        // Commit the transaction
        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {

        FragmentManager fragmentManager = getFragmentManager();

        if ( fragmentManager.getBackStackEntryCount() > 1 )
            fragmentManager.popBackStack();
        else
            super.onBackPressed();


    }
}
