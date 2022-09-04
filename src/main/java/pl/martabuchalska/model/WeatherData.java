package pl.martabuchalska.model;


public class WeatherData {
    public String main;
    public String description;
    public double temp;
    public double pressure;
    public int humidity;
    public String icon;

    public WeatherData(){};
    public WeatherData(String main, String description, double temp, double pressure, int humidity, String icon) {
        this.main = main;
        this.description = description;
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.icon = icon;
    }
}


