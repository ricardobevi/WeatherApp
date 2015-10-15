package com.ricardobevi.weatherapp.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ric on 12/10/15.
 */
public class HttpHelper {

    public interface HttpHelperCallback {
        void onDataAvailable(String data);
    }

    private static final String DEBUG_TAG = "HttpHelper";

    private HttpHelperCallback httpHelperCallback;

    private String rawStringFromURL = "";

    private String url = "";


    public HttpHelper(String url, Context context) {

        ConnectivityManager connMgr =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            this.url = url;

            this.refresh();

        } else {
            Log.e(DEBUG_TAG, "Connection Problem. Unable to fetch URL {" + url + "}");
        }

    }

    public void refresh(){
        HttpAsyncTask httpAsyncTask = new HttpAsyncTask();
        httpAsyncTask.execute(url);
    }


    private class HttpAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {

                rawStringFromURL = downloadUrl(urls[0]);

            } catch (IOException e) {
                Log.e(DEBUG_TAG, "Unable to retrieve web page. URL {" + urls[0] + "} may be invalid.", e);
            }

            return rawStringFromURL;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            httpHelperCallback.onDataAvailable(rawStringFromURL);
        }

        private String downloadUrl(String stringUrl) throws IOException {
            InputStream is = null;
            String rawString = "";

            int bufferSize = 5000;

            try {
                URL url = new URL(stringUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                // Starts the query
                conn.connect();
                int response = conn.getResponseCode();

                Log.d(DEBUG_TAG, "The response is: " + response);
                is = conn.getInputStream();

                // Convert the InputStream into a string
                rawString = readIt(is, bufferSize);

                // Makes sure that the InputStream is closed after the app is
                // finished using it.
            } finally {
                if (is != null) {
                    is.close();
                }
            }

            return rawString;
        }

        private String readIt(InputStream stream, int bufferSize) throws IOException, UnsupportedEncodingException {

            StringBuilder stringBuilder = new StringBuilder();

            BufferedReader bReader = new BufferedReader(new InputStreamReader(stream));
            String line;
            while ((line = bReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            return stringBuilder.toString();
        }

    }

    public void setHttpHelperCallback(HttpHelperCallback httpHelperCallback) {
        this.httpHelperCallback = httpHelperCallback;
    }


}
