package com.codtech;

public class Main {
    public static void main(String[] args) {
        System.out.println("Weather Data Fetcher");
        System.out.println("--------------------");
        
        WeatherService weatherService = new WeatherService();
        weatherService.start();
    }
}
