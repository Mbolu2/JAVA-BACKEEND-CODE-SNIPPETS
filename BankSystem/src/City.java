public class City {
    String cityName;
    String regionName;

    public City(String cityName, String regionName) {
        this.cityName = cityName;
        this.regionName = regionName;
    }

    @Override
    public String toString() {
        return cityName + " - " +regionName;
    }
}
