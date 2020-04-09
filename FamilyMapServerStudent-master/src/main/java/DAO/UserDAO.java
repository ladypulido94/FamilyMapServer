package DAO;

import Model.User;

import java.sql.*;

public class UserDAO {
    private Connection conn;

    /**
     * Creates the connection for the database
     * @param conn
     */
    public UserDAO(Connection conn){
        this.conn = conn;
    }

    /**
     * Inserts a new user into the database
     * @param user
     * @return commit
     * @throws DataAccessException
     */
    public void insertUser(User user) throws DataAccessException{
        //We can structure our string to be similar to a sql command, but if we insert question
        //marks we can change them later with help from the statement
        String sql = "INSERT INTO Users (Username, Password, Email, FirstName, LastName," +
                "Gender, PersonID) VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getFirstName());
            stmt.setString(5, user.getLastName());
            stmt.setString(6, user.getGender().toUpperCase());
            stmt.setString(7, user.getPersonID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while inserting into the database");
        }
    }

    /**
     * Looks in the database for the user and returns it
     * @param userName
     * @return
     * @throws DataAccessException
     */
    public User find(String userName) throws DataAccessException {
        User user;
        ResultSet rs = null;
        String sql = "SELECT * FROM Users WHERE Username = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userName);
            rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString("Username"), rs.getString("Password"),
                        rs.getString("Email"), rs.getString("FirstName"), rs.getString("LastName"),
                        rs.getString("Gender"), rs.getString("PersonID"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding user");
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
     * This method will delete the user information in the db
     * @param username
     * @throws DataAccessException
     */
    public void delete(String username) {
        String sql = "DELETE FROM Users WHERE Username = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            if(find(username) != null){
                stmt.execute();
            }
        } catch (DataAccessException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void clear() throws DataAccessException {
        try (Statement stmt = conn.createStatement()){
            String sql = "DELETE FROM Users";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while clearing Users table");
        }
    }
}
