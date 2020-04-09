package DAO;

import Model.Events;

import java.sql.*;
import java.util.ArrayList;

public class EventsDAO {

    private Connection conn;

    /**
     * Creates the connection for the database
     * @param conn
     */
    public EventsDAO(Connection conn){

        this.conn = conn;
    }

    /**
     * Inserts an event in the database
     * @param event
     * @return
     * @throws DataAccessException
     */
    public void insertEvent(Events event) throws DataAccessException {
        //We can structure our string to be similar to a sql command, but if we insert question
        //marks we can change them later with help from the statement
        String sql = "INSERT INTO Events (EventID, AssociatedUsername, PersonID, Latitude, Longitude, " +
                "Country, City, EventType, Year) VALUES(?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, event.getEventID());
            stmt.setString(2, event.getUsername());
            stmt.setString(3, event.getPersonID());
            stmt.setDouble(4, event.getLatitude());
            stmt.setDouble(5, event.getLongitude());
            stmt.setString(6, event.getCountry());
            stmt.setString(7, event.getCity());
            stmt.setString(8, event.getEventType());
            stmt.setInt(9, event.getYear());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while inserting into the database");
        }
    }

    /**
     * Looks and returns an specific event
     * @param eventID
     * @return
     * @throws DataAccessException
     */
    public Events find(String eventID) throws DataAccessException{
        Events event;
        ResultSet rs = null;
        String sql = "SELECT * FROM Events WHERE EventID = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, eventID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                event = new Events(rs.getString("EventID"), rs.getString("AssociatedUsername"),
                        rs.getString("PersonID"), rs.getFloat("Latitude"), rs.getFloat("Longitude"),
                        rs.getString("Country"), rs.getString("City"), rs.getString("EventType"),
                        rs.getInt("Year"));
                return event;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding event");
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    /**
     * Get all the events of the user's family
     * @param userName
     * @return
     * @throws DataAccessException
     */
    public ArrayList<Events> findAll(String userName) throws DataAccessException{
        Events events;
        ResultSet rs = null;
        ArrayList<Events> allEvents = new ArrayList<>();
        String sql = "SELECT * FROM Events WHERE AssociatedUsername = ?;";

        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, userName);
            rs = stmt.executeQuery();
            while (rs.next()) {
                events = new Events(rs.getString("EventID"), rs.getString("AssociatedUsername"),
                        rs.getString("PersonID"), rs.getFloat("Latitude"), rs.getFloat("Longitude"),
                        rs.getString("Country"), rs.getString("City"), rs.getString("EventType"),
                        rs.getInt("Year"));
                allEvents.add(events);
            }

        } catch (SQLException e){
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding all the events.");
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return allEvents;
    }

    /**
     * This method will delete only one event
     * @param eventID
     * @return
     * @throws DataAccessException
     */
    public void delete (String eventID) throws DataAccessException{
        String sql = "DELETE FROM Events WHERE EventID = ?;";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, eventID);
            if(find(eventID) != null){
                stmt.execute();
            }

        }catch (DataAccessException | SQLException e){
            e.printStackTrace();
        }

    }

    public void clear() throws DataAccessException {
        try (Statement stmt = conn.createStatement()){
            String sql = "DELETE FROM Event";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while clearing Event table");
        }
    }

}
