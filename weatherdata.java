package com.codtech.models;

public class WeatherData {
    private String cityName;
    private double temperature;
    private double feelsLike;
    private int humidity;
    private double windSpeed;
    private String description;

    // Getters and Setters
    public String getCityName() { return cityName; }
    public void setCityName(String cityName) { this.cityName = cityName; }
    
    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }
    
    public double getFeelsLike() { return feelsLike; }
    public void setFeelsLike(double feelsLike) { this.feelsLike = feelsLike; }
    
    public int getHumidity() { return humidity; }
    public void setHumidity(int humidity) { this.humidity = humidity; }
    
    public double getWindSpeed() { return windSpeed; }
    public void setWindSpeed(double windSpeed) { this.windSpeed = windSpeed; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
