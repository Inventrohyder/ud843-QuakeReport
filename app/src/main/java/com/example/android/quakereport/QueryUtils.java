package com.example.android.quakereport;

import android.text.TextUtils;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
final class QueryUtils {

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    static String makeHttpRequest(URL url) throws IOException {

        String jsonResponse = "";

        if(url == null){
            return jsonResponse;
        }

        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;

        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.connect();

            if(httpURLConnection.getResponseCode() == 200) {

                inputStream = httpURLConnection.getInputStream();

                jsonResponse = readStream(inputStream);

                Log.w("JSon repsonse", jsonResponse);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(httpURLConnection != null){
                httpURLConnection.disconnect();
            }
            if(inputStream != null){
                inputStream.close();
            }
        }

        return jsonResponse;

    }

    private static String readStream(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line);
                line = bufferedReader.readLine();
            }
        }
        return stringBuilder.toString();
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }


    /**
     * Return a list of {@link Earthquake} objects that has been built up from
     * parsing a JSON response.
     */
    static ArrayList<Earthquake> extractEarthquakes(String repsonse) {

        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<Earthquake> earthquakes = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.

            // Convert SAMPLE_JSON_RESPONSE String into a JSONObject
            JSONObject baseJsonResponse = new JSONObject(repsonse);

            // Extract the "features" JSONArray
            JSONArray earthquakeArray = baseJsonResponse.getJSONArray("features");


            // Loop through each feature in the {@link JSONArray}
            for (int i = 0; i < earthquakeArray.length(); i++) {

                // Get the earthquake {@link JSONObject} at position i
                JSONObject earthquake = earthquakeArray.getJSONObject(i);

                // Get the properties {@link JSONObject} that is part of the earthquake
                // {@link JSONObject}
                JSONObject properties = earthquake.getJSONObject("properties");


                // Get mag from the properties {@link JSONObject}
                double magnitude = properties.getDouble("mag");

                // Get place from the properties {@link JSONObject}
                String location = properties.getString("place");

                // Get time from the properties {@link JSONObject} as milliseconds
                long timeInMilliseconds = properties.getLong("time");

                String url = properties.getString("url");

                // Add a new {@link Earthquake} object to our earthquakes {@link ArrayList}
                earthquakes.add(new Earthquake(magnitude, location, timeInMilliseconds, url));

            }


        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }

    static List fetchEarthquakeData(String url)  {

        if(TextUtils.isEmpty(url)){
            return null;
        }

        String response = null;
        try {
            response = makeHttpRequest(createUrl(url));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return extractEarthquakes(response);

    }

}