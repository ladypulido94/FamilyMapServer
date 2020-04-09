package Request;

public class LoginRequest {

    private String userName;
    private String password;

    /**
     * This method will store the username and password passed from the client
     * @param userName
     * @param password
     */
    public LoginRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }


    /**
     * This will return the username to the login service
     * @return
     */
    public String getUsername() {
        return userName;
    }

    /**
     * This method will store the username value
     * @param userName
     */
    public void setUsername(String userName) {
        this.userName = userName;
    }

    /**
     * This method will return the password to the login service
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * THis will store the password value
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }


}
