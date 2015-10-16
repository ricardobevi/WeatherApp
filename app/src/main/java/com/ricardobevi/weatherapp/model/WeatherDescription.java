package com.ricardobevi.weatherapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by ric on 12/10/15.
 */
public class WeatherDescription implements Parcelable {

    private static final String DEBUG_TAG = "WeatherDescription";

    Integer id;
    String main;
    String description;
    String icon;

    public WeatherDescription(){

    }


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


    protected WeatherDescription(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(id);
        out.writeString(main);
        out.writeString(description);
        out.writeString(icon);
    }

    public void readFromParcel(Parcel in) {
        id = in.readInt();
        main = in.readString();
        description = in.readString();
        icon = in.readString();
    }

    public static final Creator<WeatherDescription> CREATOR = new Creator<WeatherDescription>() {
        @Override
        public WeatherDescription createFromParcel(Parcel in) {
            return new WeatherDescription(in);
        }

        @Override
        public WeatherDescription[] newArray(int size) {
            return new WeatherDescription[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

}
