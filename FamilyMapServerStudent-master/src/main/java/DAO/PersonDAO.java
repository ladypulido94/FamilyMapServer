package DAO;

import Model.Person;

import java.sql.*;
import java.util.ArrayList;

public class PersonDAO {

    private Connection conn;
    /**
     * Creates the connection for the database
     * @param conn
     */
    public PersonDAO(Connection conn){
        this.conn = conn;
    }

    /**
     * Inserts a person in the database
     * @param person
     * @return
     * @throws DataAccessException
     */
    public void insertPerson(Person person) throws DataAccessException{
        //We can structure our string to be similar to a sql command, but if we insert question
        //marks we can change them later with help from the statement
        String sql = "INSERT INTO Persons (PersonID, AssociatedUsername, FirstName, LastName, Gender," +
                "FatherID, MotherID, SpouseID) VALUES(?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, person.getPersonID());
            stmt.setString(2, person.getUsername());
            stmt.setString(3, person.getFirstName());
            stmt.setString(4, person.getLastName());
            stmt.setString(5, person.getGender().toUpperCase());
            stmt.setString(6, person.getFatherID());
            stmt.setString(7, person.getMotherID());
            stmt.setString(8, person.getSpouseID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while inserting into the database.");
        }
    }

    /**
     * Looks a person by the ID and returns ONLY that person
     * @param personID
     * @return
     * @throws DataAccessException
     */

    public Person findPerson(String personID) throws DataAccessException{
        Person person;
        ResultSet rs = null;
        String sql = "SELECT * FROM Persons WHERE PersonID = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, personID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                person = new Person(rs.getString("PersonID"), rs.getString("AssociatedUsername"),
                        rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Gender"),
                        rs.getString("FatherID"), rs.getString("MotherID"), rs.getString("SpouseID"));
                return person;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding the person.");
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
     * Looks and returns all persons related to the user
     * @param personID
     * @return
     */
    public ArrayList<Person> findAll(String personID) throws DataAccessException{
        Person person = findPerson(personID);
        String userName = person.getUsername();
        ResultSet rs = null;
        ArrayList<Person> allPeople = new ArrayList<>();
        String sql = "SELECT * FROM Persons WHERE AssociatedUsername = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userName);
            rs = stmt.executeQuery();
            while (rs.next()) {
                person = new Person(rs.getString("PersonID"), rs.getString("AssociatedUsername"),
                        rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Gender"),
                        rs.getString("FatherID"), rs.getString("MotherID"), rs.getString("SpouseID"));
                allPeople.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding all the people.");
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return allPeople;
    }

    /**
     * This method will delete only the person who has the ID
     * @param personID
     * @return
     * @throws DataAccessException
     */
    public void delete(String personID) throws DataAccessException{
        String sql = "DELETE FROM Persons WHERE PersonID = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, personID);
            if(findPerson(personID) != null){
                stmt.execute();
            }
        } catch (DataAccessException | SQLException e) {
            e.printStackTrace();
        }
    }


    public void clear() throws DataAccessException {
        try (Statement stmt = conn.createStatement()){
            String sql = "DELETE FROM Persons";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while clearing Persons table");
        }
    }

}
