package Model;


public class Events {
     private String eventID;
     private String associatedUsername;
     private String personID;
     private double latitude;
     private double longitude;
     private String country;
     private String city;
     private String eventType;
     private int year;

    /**
     * This method will store the values of the params passed from the EventDAO
     * @param eventID
     * @param userName
     * @param personID
     * @param latitude
     * @param longitude
     * @param country
     * @param city
     * @param eventType
     * @param year
     */
     public Events(String eventID, String userName, String personID, double latitude, double longitude, String country, String city, String eventType, int year) {
        this.eventID = eventID;
        this.associatedUsername = userName;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
    }

    /**
     * This method will return the EventID to the client
     * @return
     */
    public String getEventID() {
        return eventID;
    }

    /**
     * This method will store the eventID value
     * @param eventID
     */
    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    /**
     * THis method will return the Associated Username to the client
     * @return
     */
    public String getUsername() {
        return associatedUsername;
    }

    /**
     * This method will store the associated Username value
     * @param username
     */
    public void setUsername(String username) {
        this.associatedUsername = username;
    }

    /**
     * This method will return the PersonID to the client
     * @return
     */
    public String getPersonID() {
        return personID;
    }

    /**
     * This method will store the personID value
     * @param personID
     */
    public void setPersonID(String personID) {
        this.personID = personID;
    }

    /**
     * This method will return the latitude to the client
     * @return
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * This method will store the latitude value
     * @param latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * This method will return the longitude to the client
     * @return
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * This method will store the longitude value
     * @param longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * This method will return the country to the client
     * @return
     */
    public String getCountry() {
        return country;
    }

    /**
     * This method will store the country value
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * This method will return the city to the client
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     * This method will store the city value
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * This method will return the event type to the client
     * @return
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * This method will store the Event Type value
     * @param eventType
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    /**
     * This method will return the year to the client
     * @return
     */
    public int getYear() {
        return year;
    }

    /**
     * This method will store the year value
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o instanceof Events) {
            Events oEvent = (Events) o;
            return oEvent.getEventID().equals(getEventID()) &&
                    oEvent.getUsername().equals(getUsername()) &&
                    oEvent.getPersonID().equals(getPersonID()) &&
                    oEvent.getLatitude() == (getLatitude()) &&
                    oEvent.getLongitude() == (getLongitude()) &&
                    oEvent.getCountry().equals(getCountry()) &&
                    oEvent.getCity().equals(getCity()) &&
                    oEvent.getEventType().equals(getEventType()) &&
                    oEvent.getYear() == (getYear());
        } else {
            return false;
        }
    }


}

