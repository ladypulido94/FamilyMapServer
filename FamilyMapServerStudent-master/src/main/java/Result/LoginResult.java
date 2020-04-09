package Result;

public class LoginResult {

    private String userName;
    private String token;
    private String personID;
    private String message;
    private boolean success;

    /**
     * This will store the values from the Login Services
     * @param userName
     * @param token
     * @param personID
     */
    public LoginResult(String userName, String token, String personID) {
        this.userName = userName;
        this.token = token;
        this.personID = personID;
        this.success = true;
    }

    /**
     * This will store the message from the login services
     * @param message
     */
    public LoginResult(String message) {
        this.message = message;
        this.success = false;
    }

    /**
     * This will return the username to the client
     * @return
     */
    public String getUsername() {
        return userName;
    }

    /**
     * This will store the username from the login service
     * @param userName
     */
    public void setUsername(String userName) {
        this.userName = userName;
    }

    /**
     * This will return the auth token to the client
     * @return
     */
    public String getToken() {
        return token;
    }

    /**
     * This will store the auth token from the login service
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * This will return the person ID to the client
     * @return
     */
    public String getPersonID() {
        return personID;
    }

    /**
     * This will store the person ID from the login service
     * @param personID
     */
    public void setPersonID(String personID) {
        this.personID = personID;
    }

    /**
     * This will return the message to the client
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * This will store the message from the login service
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess (boolean success){
        this.success = success;
    }
}
