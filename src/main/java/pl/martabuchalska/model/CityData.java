package pl.martabuchalska.model;

public class CityData  {

    public String name;
    public double lat;
    public double lon;
    public String country;
    public String state;

    public CityData(){};
    public CityData(String name, double lat, double lon, String country, String state) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.country = country;
        this.state = state;
    }
}
