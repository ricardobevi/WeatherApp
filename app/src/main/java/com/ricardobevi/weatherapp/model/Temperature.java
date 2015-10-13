package com.ricardobevi.weatherapp.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ric on 12/10/15.
 */
public class Temperature {

    private static final String DEBUG_TAG = "Temperature";

    Double day;
    Double min;
    Double max;
    Double morn;
    Double eve;
    Double night;


    public static Temperature createFromJSON(JSONObject jsonObject) {
        Temperature temperature = null;

        try {

            temperature.min = jsonObject.getDouble("min");
            temperature.max = jsonObject.getDouble("max");

            temperature.morn = jsonObject.getDouble("morn");
            temperature.day = jsonObject.getDouble("day");
            temperature.eve = jsonObject.getDouble("eve");
            temperature.night = jsonObject.getDouble("night");


        } catch (JSONException e) {
            Log.e(DEBUG_TAG, "Error Parsing JSON", e);
        }

        return temperature;
    }

    public Double getDay() {
        return day;
    }

    public void setDay(Double day) {
        this.day = day;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getMorn() {
        return morn;
    }

    public void setMorn(Double morn) {
        this.morn = morn;
    }

    public Double getEve() {
        return eve;
    }

    public void setEve(Double eve) {
        this.eve = eve;
    }

    public Double getNight() {
        return night;
    }

    public void setNight(Double night) {
        this.night = night;
    }
}
