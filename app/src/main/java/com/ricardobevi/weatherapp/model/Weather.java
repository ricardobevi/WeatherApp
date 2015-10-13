package com.ricardobevi.weatherapp.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by ric on 12/10/15.
 */
public class Weather {

    private static final String DEBUG_TAG = "Weather";

    Date date;
    Temperature temp;
    Double pressure;
    Integer humidity;
    WeatherDescription weather;
    Double speed;
    Integer deg;
    Integer clouds;


    public static Weather createFromJSON(JSONObject jsonObject) {
        Weather weather = new Weather();

        try {
            Long rawDate = jsonObject.getLong("dt");
            weather.date = new Date(rawDate);

            weather.pressure = jsonObject.getDouble("pressure");
            weather.humidity = jsonObject.getInt("humidity");
            weather.speed = jsonObject.getDouble("speed");
            weather.deg = jsonObject.getInt("deg");
            weather.clouds = jsonObject.getInt("clouds");

            //It's only one element array, this is odd.
            JSONObject weatherJsonObject = jsonObject.getJSONArray("weather").getJSONObject(0);
            weather.weather = WeatherDescription.createFromJSON(weatherJsonObject);

            JSONObject temperatureJsonObject = jsonObject.getJSONObject("temp");
            weather.temp = Temperature.createFromJSON(temperatureJsonObject);


        } catch (JSONException e) {
            Log.e(DEBUG_TAG, "Error Parsing JSON", e);
        }

        return weather;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Temperature getTemp() {
        return temp;
    }

    public void setTemp(Temperature temp) {
        this.temp = temp;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public WeatherDescription getWeather() {
        return weather;
    }

    public void setWeather(WeatherDescription weather) {
        this.weather = weather;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getDeg() {
        return deg;
    }

    public void setDeg(Integer deg) {
        this.deg = deg;
    }

    public Integer getClouds() {
        return clouds;
    }

    public void setClouds(Integer clouds) {
        this.clouds = clouds;
    }
}
