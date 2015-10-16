package com.ricardobevi.weatherapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ric on 12/10/15.
 */
public class Temperature implements Parcelable {

    private static final String DEBUG_TAG = "Temperature";

    Double day;
    Double min;
    Double max;
    Double morn;
    Double eve;
    Double night;


    public Temperature(){

    }

    public static Temperature createFromJSON(JSONObject jsonObject) {
        Temperature temperature = new Temperature();

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



    protected Temperature(Parcel in) {
        readFromParcel(in);
    }


    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeDouble(day);
        out.writeDouble(min);
        out.writeDouble(max);
        out.writeDouble(morn);
        out.writeDouble(eve);
        out.writeDouble(night);
    }

    public void readFromParcel(Parcel in) {
        day = in.readDouble();
        min = in.readDouble();
        max = in.readDouble();
        morn = in.readDouble();
        eve = in.readDouble();
        night = in.readDouble();
    }

    public static final Creator<Temperature> CREATOR = new Creator<Temperature>() {
        @Override
        public Temperature createFromParcel(Parcel in) {
            return new Temperature(in);
        }

        @Override
        public Temperature[] newArray(int size) {
            return new Temperature[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


}
