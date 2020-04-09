package Result;

import Model.Events;

public class EventResult {

    private String associatedUsername;
    private String eventID;
    private String personID;
    private double latitude;
    private double longitude;
    private String country;
    private String city;
    private String evenType;
    private int year;
    private String message;
    private boolean success;

    /**
     * This will store the values from the Event Service
     * @param associatedUsername
     * @param eventID
     * @param personID
     * @param latitude
     * @param longitude
     * @param country
     * @param city
     * @param evenType
     * @param year
     */
    public EventResult(String associatedUsername, String eventID, String personID, double latitude, double longitude, String country, String city, String evenType, int year) {
        this.associatedUsername = associatedUsername;
        this.eventID = eventID;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.evenType = evenType;
        this.year = year;
        this.success = true;
    }

    public EventResult(Events events){
        this.associatedUsername = events.getUsername();
        this.eventID = events.getEventID();
        this.personID = events.getPersonID();
        this.latitude = events.getLatitude();
        this.longitude = events.getLongitude();
        this.country = events.getCountry();
        this.city = events.getCity();
        this.evenType = events.getEventType();
        this.year = events.getYear();
        this.success = true;
    }

    /**
     * This will store the message to display in the client
     * @param message
     */
    public EventResult(String message) {
        this.message = message;
        this.success = false;
    }

    /**
     * This will get the Associated username to the client
     * @return
     */
    public String getAssociatedUsername() {
        return associatedUsername;
    }

    /**
     * This will store the associated username value get from the client
     * @param associatedUsername
     */
    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    /**
     * This will get the event id to the client
     * @return
     */
    public String getEventID() {
        return eventID;
    }

    /**
     * This will store the event id value get from the client
     * @param eventID
     */
    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    /**
     * This will get the person id to the client
     * @return
     */
    public String getPersonID() {
        return personID;
    }

    /**
     * This will store the person id value get from the client
     * @param personID
     */
    public void setPersonID(String personID) {
        this.personID = personID;
    }

    /**
     * This will get the latitude to the client
     * @return
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * This will store the latitude value get from the client
     * @param latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * This will get the longitude to the client
     * @return
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * This will store the longitude value get from the client
     * @param longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * This will get the country to the client
     * @return
     */
    public String getCountry() {
        return country;
    }

    /**
     * This will store the country value get from the client
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * This will get the city to the client
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     * This will store the city value get from the client
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * This will get the event type to the client
     * @return
     */
    public String getEvenType() {
        return evenType;
    }

    /**
     * This will store the event type value get from the client
     * @param evenType
     */
    public void setEvenType(String evenType) {
        this.evenType = evenType;
    }

    /**
     * This will get the year to the client
     * @return
     */
    public int getYear() {
        return year;
    }

    /**
     * This will store the year value get from the client
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * This will get the message to the client
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * This will store the message value
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
