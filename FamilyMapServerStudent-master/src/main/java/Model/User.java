package Model;

public class User {
    private String userName;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String personID;

    /**
     * This method will store the values of the params passed from the UserDAO
     * @param userName
     * @param password
     * @param email
     * @param firstName
     * @param lastName
     * @param gender
     * @param personID
     */
    public User(String userName, String password, String email, String firstName, String lastName, String gender, String personID) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.personID = personID;
    }

    /**
     * This method will return the username to the client
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
     * This method will return the password to the client
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method will store the password value
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method will return the email to the client
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method will store the email value
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This method will return the first name to the client
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * This method will store the first name value
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * This method will return the last name to the client
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * This method will store the last name value
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * This method will return the gender to the client
     * @return
     */
    public String getGender() {
        return gender;
    }

    /**
     * This method will store the gender value
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * This method will return the PersonID to the client
     * @return
     */
    public String getPersonID() {
        return personID;
    }

    /**
     * This method will store the personID value
     * @param personID
     */
    public void setPersonID(String personID) {
        this.personID = personID;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o instanceof User) {
            User oUser = (User) o;
            return
                    oUser.getUsername().equals(getUsername()) &&
                    oUser.getPassword().equals(getPassword()) &&
                    oUser.getEmail().equals(getEmail()) &&
                    oUser.getFirstName().equals(getFirstName()) &&
                    oUser.getLastName().equals(getLastName()) &&
                    oUser.getGender().equals(getGender()) &&
                    oUser.getPersonID().equals(getPersonID());
        } else {
            return false;
        }
    }

}
