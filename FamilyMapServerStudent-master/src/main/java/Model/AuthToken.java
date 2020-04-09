package Model;

import java.util.UUID;

public class AuthToken {
    private String token;
    private String username;

    /**
     * THis method will store the token and username passed from the Auth DAO
     * @param token
     * @param username
     */
    public AuthToken(String token, String username) {
        this.token = token;
        this.username = username;
    }

    public AuthToken(String username){
        this.username = username;
        this.token = UUID.randomUUID().toString().substring(0,9);
    }

    /**
     * This method will return the token value
     * @return
     */
    public String getToken() {
        return token;
    }

    /**
     * This method will store the value of the token given by the user
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * This method will return the username value
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method will store the username value
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

}
