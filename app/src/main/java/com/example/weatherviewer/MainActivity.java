package com.example.weatherviewer;

import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "WEATHER";
    private TextView cityLabel, cityChanger, myLocation, temperature, weatherDetails, windLabel,
            pressureLabel, windView, pressureView, humidityLabel, rainyLabel, humidityView, rainyView;
    private Switch degreeSwitcher;
    private ImageView weatherIcon;
    private double tempC, pressureMm;
    private int direction;
    String windDirection;
    //final EditText edt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //edt = (EditText) findViewById(R.id.cityEdtText);
        cityLabel = (TextView) findViewById(R.id.city_label);
        cityChanger = (TextView) findViewById(R.id.city_changer);
        myLocation = (TextView) findViewById(R.id.my_location);
        temperature = (TextView) findViewById(R.id.temperature_view);
        weatherDetails = (TextView) findViewById(R.id.weather_details);
        windLabel = (TextView) findViewById(R.id.wind_label);
        pressureLabel = (TextView) findViewById(R.id.pressure_label);
        windView = (TextView) findViewById(R.id.wind_view);
        pressureView = (TextView) findViewById(R.id.pressure_view);
        humidityLabel = (TextView) findViewById(R.id.humidity_label);
        rainyLabel = (TextView) findViewById(R.id.rainy_label);
        humidityView = (TextView) findViewById(R.id.humidity_view);
        rainyView = (TextView) findViewById(R.id.rainy_view);
        degreeSwitcher = (Switch) findViewById(R.id.degree_switcher);
        weatherIcon = (ImageView) findViewById(R.id.weather_icon);
        new WeatherItemsTask().execute();
     /*   edt.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    bar.setVisibility(View.VISIBLE);
                    JSONSearchTask task = new JSONSearchTask();
                    String pattern = edt.getEditableText().toString();
                    task.execute(new String[]{pattern});
                    return true;
                }

                return false;
            }
        }); */
    }

    private class WeatherItemsTask extends AsyncTask<Void, Void, WeatherData> {
        @Override
        protected WeatherData doInBackground(Void... params) {
            return new OpenWeatherFetchr().fetchItems();
        }

        @Override
        protected void onPostExecute(WeatherData weatherData) {
            //Weather data from site to app
            tempC = weatherData.getTemperature() - 273.0;
            pressureMm = (weatherData.getPressure()) * 0.75;
            direction = weatherData.getWindDirectionNum(weatherData.getDegWind());
                if (direction == 1) { windDirection = getString(R.string.wind1);}
                else if (direction == 2) {windDirection = getString(R.string.wind2);}
                else if (direction == 3){ windDirection = getString(R.string.wind3);}
                else if (direction == 4){ windDirection = getString(R.string.wind4);}
                else if (direction == 5){ windDirection = getString(R.string.wind5);}
                else if (direction == 6){ windDirection = getString(R.string.wind6);}
                else if (direction == 7){ windDirection = getString(R.string.wind7);}
                else if (direction == 8){ windDirection = getString(R.string.wind8);}
            String pressureStr = String.format("%.0f", pressureMm) + " " + getString(R.string.pressure);
            String tempStr = String.format("%.1f", tempC) + " " + getString(R.string.temperatureC);
            String wind = String.format(weatherData.getWindSpeed() + " " + getString(R.string.windSpeed));
            String humidity = weatherData.getHumidity() + "%";

            windView.setText(wind + " " + windDirection);
            temperature.setText(tempStr);
            weatherDetails.setText(weatherData.getWeatherDetails());
            pressureView.setText(pressureStr);
            humidityView.setText(humidity);
            // End of weather data fetching

            //getting City name

            // finish City name
        }
    }
}
