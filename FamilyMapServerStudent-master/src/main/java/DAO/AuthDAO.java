package DAO;

import Model.AuthToken;
import Model.User;

import javax.xml.crypto.Data;
import java.sql.*;

public class AuthDAO {

    private Connection conn;

    /**
     * Creates the connection for the database
     * @param conn
     */
    public AuthDAO(Connection conn){
        this.conn = conn;
    }

    /**
     * Creates a token for the username
     * @param token
     * @return
     * @throws DataAccessException
     */
    public void insert (AuthToken token) throws DataAccessException{
        String sql = "INSERT INTO AuthTokens (Token, Username) VALUES (?,?)";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, token.getToken());
            stmt.setString(2, token.getUsername());

            stmt.execute();
        } catch (SQLException e){
            throw new DataAccessException("Error encountered while inserting into database");
        }
    }

    /**
     * Returns the token that corresponds to the user
     * @param token
     * @return
     * @throws DataAccessException
     */
    public AuthToken find(String token) throws DataAccessException {
        AuthToken authToken;
        ResultSet rs = null;
        String sql = "SELECT * FROM AuthTokens WHERE Token = ?;";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, token);
            rs = stmt.executeQuery();
            if(rs.next()){
                authToken = new AuthToken(rs.getString("Token"),rs.getString("Username"));
                return authToken;
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding a token");
        } finally {
            if(rs != null){
                try{
                    rs.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * THis method will delete the authtoken of the user
     * @param token
     * @return
     * @throws DataAccessException
     */
    public void deleteAuthToken(String token) throws DataAccessException{
        String sql = "DELETE FROM AuthTokens WHERE Token = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, token);
            if(find(token) != null){
                stmt.execute();
            }

        } catch (SQLException | DataAccessException e){
            e.printStackTrace();
        }
    }

    public void clear() throws DataAccessException {
        try (Statement stmt = conn.createStatement()){
            String sql = "DELETE FROM AuthTokens";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while clearing AuthToken tables");
        }
    }
}
