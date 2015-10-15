package com.ricardobevi.weatherapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ricardobevi.weatherapp.R;
import com.ricardobevi.weatherapp.model.Forecast;
import com.ricardobevi.weatherapp.model.Weather;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by ric on 14/10/15.
 */
public class ForecastArrayAdapter extends ArrayAdapter<Weather> {

    private final Context context;
    private final List<Weather> weatherList;

    static class ViewHolder {
        ImageView weatherImage;
        TextView weatherWeekday;
        TextView weatherDate;
        TextView weatherTemperatureMax;
        TextView weatherTemperatureMin;
    }


    public ForecastArrayAdapter(Context context, int resource, List<Weather> objects) {
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

            SimpleDateFormat sdfWeekday = new SimpleDateFormat( "EEEE" );
            SimpleDateFormat sdfDate = new SimpleDateFormat( "d/MM/yyyy" );

            holder.weatherWeekday.setText( sdfWeekday.format(weather.getDate()) );
            holder.weatherDate.setText( sdfDate.format(weather.getDate()) );

            Double maxTemp = Math.round( weather.getTemp().getMax() ) * 1.0;
            Double minTemp = Math.round( weather.getTemp().getMin() ) * 1.0;

            DecimalFormat df = new DecimalFormat("0");
            String maxTempString = df.format(maxTemp);
            String minTempString = df.format(minTemp);

            holder.weatherTemperatureMax.setText(
                    maxTempString + "°C"
            );

            holder.weatherTemperatureMin.setText(
                    minTempString + "°C"
            );
        }


        return convertView;
    }
}
