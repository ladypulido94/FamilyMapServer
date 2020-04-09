package Result;

public class RegisterResult {

    private String authToKen;
    private String userName;
    private String personID;
    private String message;
    private boolean success;
    /**
     * This will store the values from the register services
     * @param authToKen
     * @param userName
     * @param personID
     */
    public RegisterResult(String authToKen, String userName, String personID) {
        this.authToKen = authToKen;
        this.userName = userName;
        this.personID = personID;
        this.success = true;
    }

    /**
     * This will store the message from the register services
     * @param message
     */
    public RegisterResult(String message) {
        this.message = message;
        this.success = false;
    }

    /**
     * This will return the auth token to the client
     * @return
     */
    public String getAuthToKen() {
        return authToKen;
    }

    /**
     * This will store the auth token from the register services
     * @param authToKen
     */
    public void setAuthToKen(String authToKen) {
        this.authToKen = authToKen;
    }

    /**
     * This will return the username to the client
     * @return
     */
    public String getUsername() {
        return userName;
    }

    /**
     * This will store the username from the register services
     * @param userName
     */
    public void setUsername(String userName) {
        this.userName = userName;
    }

    /**
     * This will return the person ID to the client
     * @return
     */
    public String getPersonID() {
        return personID;
    }

    /**
     * This will store the person ID from the register services
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
     * This will store the message from the register services
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
