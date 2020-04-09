package Request;

public class RegisterRequest {

    private String userName;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;

    /**
     * This method will store the values from the register form display in the client
     * @param userName
     * @param password
     * @param email
     * @param firstName
     * @param lastName
     * @param gender
     */
    public RegisterRequest(String userName, String password, String email, String firstName, String lastName, String gender) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    /**
     * This will return the username to the client
     * @return
     */
    public String getUsername() {
        return userName;
    }

    /**
     * This will store the user value from the client
     * @param userName
     */
    public void setUsername(String userName) {
        this.userName = userName;
    }

    /**
     * This will return the password to the register service
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * This will store the password value from the client
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This will return the email to the reguster service
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * This will store the email value from the client
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This will return the first name to the register service
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * This will store the first name value from the client
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * This will return the last name to the register service
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * This will store the last name value from the client
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * This will return the gender to the register service
     * @return
     */
    public String getGender() {
        return gender;
    }

    /**
     * This will store the gender value from the client
     * @param gender
     */
    public void setGender(String  gender) {
        this.gender = gender;
    }

}
