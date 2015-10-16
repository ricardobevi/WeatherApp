package com.ricardobevi.weatherapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;

/**
 * Created by ric on 12/10/15.
 */
public class Forecast {

    private static final String DEBUG_TAG = "Forecast";

    ArrayList<Weather> weatherList;
    City city;
    Integer cnt;


    public static Forecast createFromJSONString(String jsonString){

        Forecast forecast = new Forecast();
        forecast.weatherList = new ArrayList<Weather>();

        Log.d(DEBUG_TAG,"JSONString to parse: " + jsonString);

        try {

            JSONObject object = (JSONObject) new JSONTokener(jsonString).nextValue();
            forecast.cnt = object.getInt("cnt");

            forecast.city = City.createFromJSON(object.getJSONObject("city"));

            JSONArray weatherList = object.getJSONArray("list");

            for (int i = 0; i < weatherList.length(); i++) {
                JSONObject weatherListJSONObject = weatherList.getJSONObject(i);
                Weather weather = Weather.createFromJSON(weatherListJSONObject);
                forecast.weatherList.add( weather );
            }

        } catch (JSONException e){
            Log.e(DEBUG_TAG, "Error Parsing JSON", e);
        }

        return forecast;

    }

    public Forecast() {
        weatherList = new ArrayList<Weather>();
        city = new City();
        cnt = 0;
    }





    public ArrayList<Weather> getWeatherList() {
        return weatherList;
    }

    public void setWeatherList(ArrayList<Weather> weatherList) {
        this.weatherList = weatherList;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }


    public void copy( Forecast forecast_copy ){
        weatherList = forecast_copy.weatherList;
        city = forecast_copy.city;
        cnt = forecast_copy.cnt;
    }


}
