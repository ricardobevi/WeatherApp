package com.ricardobevi.weatherapp.model;

import android.util.Log;
import android.util.Pair;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ric on 12/10/15.
 */
public class City {

    private static final String DEBUG_TAG = "City";

    Integer id;
    String name;
    Pair<Double, Double> coord;
    String country;
    Long population;

    public static City createFromJSON(JSONObject jsonObject){
        City city = new City();

        try {

            city.id = jsonObject.getInt("id");
            city.name = jsonObject.getString("name");
            city.country = jsonObject.getString("country");
            city.population = jsonObject.getLong("population");

            JSONObject coordJsonObject = jsonObject.getJSONObject("coord");

            Double lat = coordJsonObject.getDouble("lat");
            Double lon = coordJsonObject.getDouble("lon");
            city.coord = new Pair<Double,Double>(lat, lon);

        } catch (JSONException e) {
            Log.e(DEBUG_TAG, "Error Parsing JSON", e);
        }

        return city;
    }

    public City() {
    }

    public City(Integer id, String name, Pair<Double, Double> coord, String country, Long population) {
        this.id = id;
        this.name = name;
        this.coord = coord;
        this.country = country;
        this.population = population;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pair<Double, Double> getCoord() {
        return coord;
    }

    public void setCoord(Pair<Double, Double> coord) {
        this.coord = coord;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }


    @Override
    public String toString() {

        StringBuilder cityString = new StringBuilder();

        cityString
                .append(name)
                .append(", ")
                .append(country)
                .append("\n lon:")
                .append(coord.first)
                .append(" lat:")
                .append(coord.second);

        return cityString.toString();
    }
}
