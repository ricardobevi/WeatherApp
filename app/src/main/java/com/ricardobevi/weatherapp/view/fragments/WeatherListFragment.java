package com.ricardobevi.weatherapp.view.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ricardobevi.weatherapp.R;
import com.ricardobevi.weatherapp.helper.HttpHelper;

/**
 * A placeholder fragment containing a simple view.
 */
public class WeatherListFragment extends Fragment {

    TextView textView;
    HttpHelper httpHelper;

    public WeatherListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        httpHelper = new HttpHelper( getString(R.string.infoUrl), getActivity().getApplicationContext());

        httpHelper.setHttpHelperCallback( new HttpHelper.HttpHelperCallback() {
            @Override
            public void onDataAvailable(String data) {
                setTextInfo(data);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_weather_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = (TextView) view.findViewById(R.id.info_text);
    }

    private void setTextInfo(String text){
        if ( textView != null )
            textView.setText(text);
    }
}
