package com.ricardobevi.weatherapp.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by ric on 12/10/15.
 */
public class WeatherDescription {

    private static final String DEBUG_TAG = "WeatherDescription";

    Integer id;
    String main;
    String description;
    String icon;

    public static WeatherDescription createFromJSON(JSONObject jsonObject) {
        WeatherDescription weatherDescription = new WeatherDescription();

        try {

            weatherDescription.id = jsonObject.getInt("id");
            weatherDescription.main = jsonObject.getString("main");
            weatherDescription.description = jsonObject.getString("description");

            //TODO: get the actual icon
            weatherDescription.icon = jsonObject.getString("icon");

        } catch (JSONException e) {
            Log.e(DEBUG_TAG, "Error Parsing JSON", e);
        }

        return weatherDescription;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
