package com.example.weatherviewer;

public class WeatherData {
private String iconId;
private double temperature;
private String weatherDetails;
private int windSpeed;
private int degWind;
private int pressure;
private int humidity;
// opportunity of rain???

    public String getIconId() {
        return iconId;
    }

    public void setIconId(String iconId) {
        this.iconId = iconId;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getWeatherDetails() {
        return weatherDetails;
    }

    public void setWeatherDetails(String weatherDetails) {
        this.weatherDetails = weatherDetails;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getDegWind() {
        return degWind;
    }

    public void setDegWind(int degWind) {
        this.degWind = degWind;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getWindDirectionNum(int degWind){
       int direction = 0;
       if(degWind<45){
          direction =1; 
       }
        if(degWind>=45&&degWind<90){
            direction =2;
        }
        if(degWind>=90&&degWind<135){
            direction =3;
        }
        if(degWind>=135&&degWind<180){
            direction =4;
        }
        if(degWind>=180&&degWind<225){
            direction =5;
        }
        if(degWind>=225&&degWind<270){
            direction =6;
        }
        if(degWind>=270&&degWind<315){
            direction =7;
        }
        if(degWind>=315&&degWind<360){
            direction =8;
        }
        return direction;
    }
}
