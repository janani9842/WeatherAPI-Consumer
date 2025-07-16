package com.codtech;

import com.codtech.models.WeatherData;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class WeatherService {
    private static final String API_KEY = "YOUR_API_KEY";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    public void start() {
        System.out.print("Enter city name: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        try {
            String city = reader.readLine();
            WeatherData weatherData = fetchWeatherData(city);
            
            if (weatherData != null) {
                displayWeatherData(weatherData);
            } else {
                System.out.println("Failed to fetch weather data for " + city);
            }
        } catch (IOException e) {
            System.out.println("Error reading input: " + e.getMessage());
        }
    }

    private WeatherData fetchWeatherData(String city) {
        try {
            String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
            String urlString = BASE_URL + "?q=" + encodedCity + "&appid=" + API_KEY + "&units=metric";
            URL url = new URL(urlString);
            
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (JsonReader jsonReader = Json.createReader(connection.getInputStream())) {
                    JsonObject json = jsonReader.readObject();
                    return parseWeatherData(json);
                }
            } else {
                System.out.println("API request failed with response code: " + responseCode);
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error fetching weather data: " + e.getMessage());
            return null;
        }
    }

    private WeatherData parseWeatherData(JsonObject json) {
        WeatherData data = new WeatherData();
        data.setCityName(json.getString("name"));
        
        JsonObject main = json.getJsonObject("main");
        data.setTemperature(Double.parseDouble(main.get("temp").toString()));
        data.setFeelsLike(Double.parseDouble(main.get("feels_like").toString()));
        data.setHumidity(Integer.parseInt(main.get("humidity").toString()));
        
        JsonObject wind = json.getJsonObject("wind");
        data.setWindSpeed(Double.parseDouble(wind.get("speed").toString()));
        
        data.setDescription(json.getJsonArray("weather")
                             .getJsonObject(0)
                             .getString("description"));
        
        return data;
    }

    private void displayWeatherData(WeatherData data) {
        System.out.println("\nWeather Information for " + data.getCityName());
        System.out.println("--------------------------------");
        System.out.printf("Temperature: %.1f°C\n", data.getTemperature());
        System.out.printf("Feels like: %.1f°C\n", data.getFeelsLike());
        System.out.println("Humidity: " + data.getHumidity() + "%");
        System.out.printf("Wind Speed: %.1f m/s\n", data.getWindSpeed());
        System.out.println("Conditions: " + data.getDescription());
        System.out.println("--------------------------------");
    }
}
