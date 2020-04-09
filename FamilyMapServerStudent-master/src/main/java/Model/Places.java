package Model;

public class Places {
    private String country;
    private String city;
    private double latitude;
    private double longitude;


    public Places(String country, String city, double latitude, double longitude){
        this.country = country;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o instanceof Places) {
            Places places = (Places) o;
            return places.getCountry().equals(getCountry()) &&
                    places.getCity().equals(getCity()) &&
                    places.getLatitude() == getLatitude() &&
                    places.getLongitude() == getLongitude();
        } else {
            return false;
        }
    }
}
