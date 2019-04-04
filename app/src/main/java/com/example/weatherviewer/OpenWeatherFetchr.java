package com.example.weatherviewer;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static com.example.weatherviewer.MainActivity.TAG;


public class OpenWeatherFetchr {
    public static final String OPEN_WEATHER_API_KEY = "b98a7fe71da98d7416c799da1405ac71";
    public byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() + ": with " + urlSpec);

            }
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        }finally {
            connection.disconnect();
        }
    }
    public String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }
    public WeatherData fetchItems() {
        WeatherData wd =new WeatherData();
        try {
            String url = Uri.parse("http://api.openweathermap.org/data/2.5/weather")
                    .buildUpon()
                    .appendQueryParameter("q", "Omsk")
                    .appendQueryParameter("appid","b98a7fe71da98d7416c799da1405ac71")
                    .appendQueryParameter("lang","ru")
                    .build().toString();
            String jsonString = getUrlString(url);
            Log.i(TAG, "Received JSON: " + jsonString);
            JSONObject jsonBody = new JSONObject(jsonString);
            parseItems(wd,jsonBody);
        }
        catch (JSONException joe){
            Log.e(TAG,"Failed JSON parsing",joe);
        }
        catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items", ioe);
        }
        return wd;
    }
    private void parseItems(WeatherData currentWeather,JSONObject jsonBody) throws IOException,JSONException{
        JSONArray weatherJsonArray = jsonBody.getJSONArray("weather");
        JSONObject weatherDetails = weatherJsonArray.getJSONObject(0);
        JSONObject mainJsonObject = jsonBody.getJSONObject("main");
        JSONObject windJsonObject = jsonBody.getJSONObject("wind");
        currentWeather.setWindSpeed(windJsonObject.getInt("speed"));
        currentWeather.setTemperature(mainJsonObject.getDouble("temp"));
        currentWeather.setWeatherDetails(weatherDetails.getString("description"));
        currentWeather.setPressure(mainJsonObject.getInt("pressure"));
        currentWeather.setHumidity(mainJsonObject.getInt("humidity"));
        currentWeather.setDegWind(windJsonObject.getInt("deg"));
    }
}

