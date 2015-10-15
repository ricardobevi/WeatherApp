package com.ricardobevi.weatherapp.view.fragments;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.ricardobevi.weatherapp.R;
import com.ricardobevi.weatherapp.adapter.WeatherArrayAdapter;
import com.ricardobevi.weatherapp.helper.HttpHelper;
import com.ricardobevi.weatherapp.model.Forecast;
import com.ricardobevi.weatherapp.model.Weather;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class WeatherListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    ListView weatherListView;
    TextView cityData;
    HttpHelper httpHelper;
    SwipeRefreshLayout swipeLayout;

    Forecast forecast;

    ArrayList<Weather> weatherArrayList;

    OnWeatherItemClickListener onWeatherItemClickListener;


    public interface OnWeatherItemClickListener {
        void onWeatherItemClicked(Weather weather);
    }


    public class OnItemClickListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Weather selectedItem = (Weather) weatherListView.getSelectedItem();

            onWeatherItemClickListener.onWeatherItemClicked(selectedItem);

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
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            onWeatherItemClickListener = (OnWeatherItemClickListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnWeatherItemClickListener");
        }

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
                new WeatherArrayAdapter(this.getActivity(), R.layout.weather_list_row, weatherArrayList)
        );

        weatherListView.setOnItemClickListener(new OnItemClickListener());

        cityData = (TextView) view.findViewById(R.id.cityData);
    }

    @Override
    public void onRefresh() {
        httpHelper.refresh();
    }


    private void updateForecast(String text){

        forecast = Forecast.createFromJSONString(text);

        weatherArrayList.clear();

        weatherArrayList.addAll(forecast.getWeatherList());

        if ( weatherListView != null )
            ((WeatherArrayAdapter) weatherListView.getAdapter()).notifyDataSetChanged();

        if ( cityData != null )
            cityData.setText(forecast.getCity().toString());

        if ( swipeLayout != null && swipeLayout.isRefreshing() )
            swipeLayout.setRefreshing(false);

    }
}
