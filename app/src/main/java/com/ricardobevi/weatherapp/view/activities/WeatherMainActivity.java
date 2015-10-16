package com.ricardobevi.weatherapp.view.activities;

import android.app.FragmentManager;
import android.os.Bundle;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.ricardobevi.weatherapp.R;
import com.ricardobevi.weatherapp.model.Forecast;
import com.ricardobevi.weatherapp.model.Weather;
import com.ricardobevi.weatherapp.view.fragments.WeatherDetailFragment;
import com.ricardobevi.weatherapp.view.fragments.WeatherListFragment;

public class WeatherMainActivity extends AppCompatActivity implements WeatherListFragment.OnWeatherItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        FragmentManager fragmentManager = getFragmentManager();

        if ( fragmentManager.getBackStackEntryCount() < 1 ) {

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            WeatherListFragment weatherListFragment = new WeatherListFragment();

            fragmentTransaction.replace(R.id.main_container, weatherListFragment);

            fragmentTransaction.addToBackStack(null);

            fragmentTransaction.commit();

        }

    }

    @Override
    public void onBackPressed() {

        FragmentManager fragmentManager = getFragmentManager();

        if ( fragmentManager.getBackStackEntryCount() > 1 )
            fragmentManager.popBackStack();
        else
            super.onBackPressed();


    }

    @Override
    public void onWeatherItemClicked(Weather weather) {

        WeatherDetailFragment weatherDetailFragment = new WeatherDetailFragment();

        Bundle args = new Bundle();
        args.putParcelable("weather", weather);


        weatherDetailFragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.main_container, weatherDetailFragment);

        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();

    }
}
