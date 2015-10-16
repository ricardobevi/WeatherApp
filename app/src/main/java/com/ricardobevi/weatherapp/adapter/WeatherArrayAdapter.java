package com.ricardobevi.weatherapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ricardobevi.weatherapp.R;
import com.ricardobevi.weatherapp.helper.TagFormat;
import com.ricardobevi.weatherapp.model.Forecast;
import com.ricardobevi.weatherapp.model.Weather;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by ric on 14/10/15.
 */
public class WeatherArrayAdapter extends ArrayAdapter<Weather> {

    private final Context context;
    private final List<Weather> weatherList;

    static class ViewHolder {
        ImageView weatherImage;
        TextView weatherWeekday;
        TextView weatherDate;
        TextView weatherTemperatureMax;
        TextView weatherTemperatureMin;
    }


    public WeatherArrayAdapter(Context context, int resource, List<Weather> objects) {
        super(context, resource, objects);

        this.context = context;
        this.weatherList = objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if( convertView == null ) {

            holder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.weather_list_row, parent, false);

            holder.weatherImage = (ImageView) convertView.findViewById(R.id.weather_image);
            holder.weatherWeekday = (TextView) convertView.findViewById(R.id.weather_weekday);
            holder.weatherDate = (TextView) convertView.findViewById(R.id.weather_date);
            holder.weatherTemperatureMax = (TextView) convertView.findViewById(R.id.weather_temperature_max);
            holder.weatherTemperatureMin = (TextView) convertView.findViewById(R.id.weather_temperature_min);

            convertView.setTag(holder);

        } else {

            holder = (ViewHolder) convertView.getTag();

        }


        if ( weatherList.size() > position ) {
            Weather weather = weatherList.get(position);

            populateView(weather, convertView, holder);

        }


        return convertView;
    }


    private void populateView(Weather weather, View convertView, ViewHolder holder) {

        String tempUnit = context.getString(R.string.temp_unit);

        SimpleDateFormat sdfWeekday = new SimpleDateFormat( "EEEE" );
        SimpleDateFormat sdfDate = new SimpleDateFormat( context.getString(R.string.date_format) );

        holder.weatherWeekday.setText( sdfWeekday.format(weather.getDate()) );
        holder.weatherDate.setText( sdfDate.format(weather.getDate()) );

        Double maxTemp = Math.round( weather.getTemp().getMax() ) * 1.0;
        Double minTemp = Math.round( weather.getTemp().getMin() ) * 1.0;

        DecimalFormat df = new DecimalFormat("0");
        String maxTempString = df.format(maxTemp);
        String minTempString = df.format(minTemp);

        maxTempString = TagFormat.from(context.getString(R.string.list_temp))
                .with("temp", maxTempString)
                .with("temp_unit", tempUnit)
                .format();

        minTempString = TagFormat.from(context.getString(R.string.list_temp))
                .with("temp", minTempString)
                .with("temp_unit", tempUnit)
                .format();

        holder.weatherTemperatureMax.setText( maxTempString );

        holder.weatherTemperatureMin.setText( minTempString );


        String weatherIconString = "ic_" + weather.getWeather().getIcon().substring(0,2);

        Integer weatherIconResource =
                convertView.getResources().getIdentifier(
                        weatherIconString, "drawable", context.getPackageName());

        holder.weatherImage.setImageResource(weatherIconResource);
    }
}
