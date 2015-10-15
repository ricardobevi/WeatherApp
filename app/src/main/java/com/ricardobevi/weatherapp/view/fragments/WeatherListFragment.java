package com.ricardobevi.weatherapp.view.fragments;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Handler;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ricardobevi.weatherapp.R;
import com.ricardobevi.weatherapp.adapter.ForecastArrayAdapter;
import com.ricardobevi.weatherapp.helper.HttpHelper;
import com.ricardobevi.weatherapp.model.Forecast;
import com.ricardobevi.weatherapp.model.Weather;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class WeatherListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    ListView weatherListView;
    HttpHelper httpHelper;
    SwipeRefreshLayout swipeLayout;

    Forecast forecast;

    ArrayList<Weather> weatherArrayList;


    public class OnWeatherItemClickListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            weatherListView.getSelectedItem();

            FragmentManager fragmentManager = getActivity().getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            WeatherDetailFragment weatherDetailFragment = new WeatherDetailFragment();

            fragmentTransaction.replace(R.id.main_container, weatherDetailFragment);

            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

            fragmentTransaction.addToBackStack(null);

            // Commit the transaction
            fragmentTransaction.commit();

        }

    }

    public WeatherListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        httpHelper = new HttpHelper( getString(R.string.infoUrl), getActivity().getApplicationContext());

        httpHelper.setHttpHelperCallback( new HttpHelper.HttpHelperCallback() {
            @Override
            public void onDataAvailable(String data) {
                updateForecast(data);
            }
        });

        forecast = new Forecast();
        weatherArrayList = new ArrayList<Weather>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_weather_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        weatherListView = (ListView) view.findViewById(R.id.weather_list);

        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.fragment_weather_list_swipe_refresh);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeResources(R.color.material_teal_A200);

        weatherListView.setAdapter(
                new ForecastArrayAdapter(this.getActivity(), R.layout.weather_list_row, weatherArrayList )
        );

        weatherListView.setOnItemClickListener(new OnWeatherItemClickListener());
    }

    @Override
    public void onRefresh() {
        httpHelper.refresh();
    }


    private void updateForecast(String text){

        forecast = Forecast.createFromJSONString(text);

        weatherArrayList.clear();

        weatherArrayList.addAll(forecast.getWeatherList());

        ( (ForecastArrayAdapter) weatherListView.getAdapter() ).notifyDataSetChanged();

        if ( swipeLayout.isRefreshing() )
            swipeLayout.setRefreshing(false);

    }
}
