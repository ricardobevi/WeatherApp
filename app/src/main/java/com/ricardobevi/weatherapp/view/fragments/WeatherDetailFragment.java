package com.ricardobevi.weatherapp.view.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ricardobevi.weatherapp.R;
import com.ricardobevi.weatherapp.model.Weather;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * Created by ric on 13/10/15.
 */
public class WeatherDetailFragment extends Fragment {

    Context context;

    Weather weather;

    TextView detailMain;
    TextView detailDesc;
    ImageView detailIcon;
    TextView detailAvgTemp;
    TextView detailMaxTemp;
    TextView detailMinTemp;
    TextView detailWeekday;
    TextView detailDate;
    TextView humidity;
    TextView speed;

    public WeatherDetailFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        context = getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_weather_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        detailMain = (TextView) view.findViewById(R.id.detail_main);
        detailDesc = (TextView) view.findViewById(R.id.detail_desc);
        detailIcon = (ImageView) view.findViewById(R.id.detail_icon);
        detailAvgTemp = (TextView) view.findViewById(R.id.detail_avg_temp);
        detailMaxTemp = (TextView) view.findViewById(R.id.detail_max_temp);
        detailMinTemp = (TextView) view.findViewById(R.id.detail_min_temp);
        detailWeekday = (TextView) view.findViewById(R.id.detail_weekday);
        detailDate = (TextView) view.findViewById(R.id.detail_date);
        humidity = (TextView) view.findViewById(R.id.humidity);
        speed = (TextView) view.findViewById(R.id.speed);

        Double avgTemp = (weather.getTemp().getMax() + weather.getTemp().getMin()) / 2.0;

        String humidityString = view.getResources().getString(R.string.detail_humidity);
        String speedString = view.getResources().getString(R.string.detail_speed);
        String tempUnitString = view.getResources().getString(R.string.temp_unit);
        String speedUnitString = view.getResources().getString(R.string.speed_unit);



        SimpleDateFormat sdfWeekday = new SimpleDateFormat( "EEEE" );
        SimpleDateFormat sdfDate = new SimpleDateFormat( "d/MM/yyyy" );

        Double maxTemp = Math.round( weather.getTemp().getMax() ) * 1.0;
        Double minTemp = Math.round( weather.getTemp().getMin() ) * 1.0;
        avgTemp = Math.round( avgTemp ) * 1.0;

        DecimalFormat df = new DecimalFormat("0");
        String maxTempString = df.format(maxTemp);
        String minTempString = df.format(minTemp);
        String avgTempString = df.format(avgTemp);


        String weatherIconString = "ic_" + weather.getWeather().getIcon().substring(0,2);

        Integer weatherIconResource =
                view.getResources().getIdentifier(
                        weatherIconString, "drawable", context.getPackageName());

        detailIcon.setImageResource(weatherIconResource);


        detailMain.setText( weather.getWeather().getMain() );
        detailDesc.setText( weather.getWeather().getDescription() );
        detailAvgTemp.setText( avgTempString );
        detailMaxTemp.setText( "Max: " + maxTempString + "°" + tempUnitString );
        detailMinTemp.setText( "Min: " + minTempString + "°" + tempUnitString );
        detailWeekday.setText( sdfWeekday.format(weather.getDate()) );
        detailDate.setText( sdfDate.format(weather.getDate()) );
        humidity.setText( humidityString + weather.getHumidity() );
        speed.setText( speedString + weather.getSpeed() + speedUnitString );

    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }


}
